package com.smedialink.responses

import android.content.Context
import android.util.Log
import com.smedialink.responses.data.repository.SmartBotRepository
import com.smedialink.responses.domain.model.NeuroBotType
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BotsRemoteEventsUseCase(context: Context, private val userId: Long) {

    private val disposables = CompositeDisposable()

    private val repository: SmartBotRepository = SmartBotRepository.getInstance(context)

    interface Callback {
        fun onSuccess()
    }

    fun sendAppInstalledEvent(userId: Long, callback: Callback) {
        repository.sendAppInstallEvent(userId)
            .subscribeOn(Schedulers.io())
            .subscribe({
                callback.onSuccess()
                Log.d("Remote event", "App installed event, user id $userId")
            }, { it.printStackTrace() })
            .also { disposables.add(it) }
    }

    fun sendBotInstalledEvent(type: NeuroBotType, userId: Long) {
        repository.sendBotInstallEvent(type, userId)
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("Remote event", "Bot ${type.label} installed event, user id $userId")
            }, { it.printStackTrace() })
            .also { disposables.add(it) }
    }

    fun sendBotRatingEvent(type: NeuroBotType, userId: Long, rating: Int) {
        repository.sendBotRating(type, userId, rating)
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("Remote event", "Bot ${type.label} rating $rating event, user id $userId")
                fetchStatistic()
            }, { it.printStackTrace() })
            .also { disposables.add(it) }
    }

    fun dispose() {
        disposables.clear()
    }

    fun fetchStatistic() {
        repository.fetchStatistics().subscribe({}, { it.printStackTrace() }).also { disposables.add(it) }
    }

    fun fetchVotes() {
        repository.fetchVotes(userId).subscribe({}, { it.printStackTrace() }).also { disposables.add(it) }
    }
}
