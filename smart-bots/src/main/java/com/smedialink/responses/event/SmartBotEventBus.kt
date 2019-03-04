package com.smedialink.responses.event

import com.smedialink.responses.domain.model.enums.SmartBotType
import io.reactivex.subjects.BehaviorSubject

class SmartBotEventBus {

    companion object {
        fun get(): BehaviorSubject<Event> {
            if (INSTANCE == null) {
                INSTANCE = BehaviorSubject.createDefault(Event.Empty)
            }

            return INSTANCE!!
        }

        fun flush() {
            get().onNext(Event.Empty)
        }

        private var INSTANCE: BehaviorSubject<Event>? = null
    }

    sealed class Event {
        class BotInstalled(val type: SmartBotType) : Event()
        class BotAnswerChosen(val botType: SmartBotType, val tag: String, val position: Int, val userId: Int) : Event()
        class BotsRefreshed(val refreshPanel: Boolean) : Event()
        class BotsPurchasesList(val list: List<SmartBotType>) : Event()
        object Empty : Event()
    }
}
