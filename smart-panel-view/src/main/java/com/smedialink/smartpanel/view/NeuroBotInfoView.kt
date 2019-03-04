package com.smedialink.smartpanel.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.responses.provider.IconsProvider
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.extension.pxToDp
import com.smedialink.smartpanel.model.info.BotInfoModel
import kotlinx.android.synthetic.main.bots_description_content.view.*


/**
 * Вью для отображения подробной инфы о боте
 */
@SuppressLint("ViewConstructor")
class NeuroBotInfoView(context: Context, private val delegate: NeuroBotInfoViewDelegate) : FrameLayout(context) {

    interface NeuroBotInfoViewDelegate {
        fun onCloseClick()
        fun onEnableClick()
        fun onRatingChosen(type: SmartBotType, rating: Int)
    }

    var shopItem: ShopItem? = null
        set(value) {
            if (value != null) {
                field = value
                fillInfoAboutBot(mapToBotInfo(value))
            }
        }

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.bots_description_content, this, true)

        closeNeuroBotInfo.setOnClickListener { delegate.onCloseClick() }
        addNeuroBot.setOnClickListener { delegate.onEnableClick() }
    }

    private fun fillInfoAboutBot(botInfoModel: BotInfoModel) {
        neuroBotName.text = botInfoModel.botName
        ratingNumber.text = botInfoModel.averageRating.toString()
        responses.text = "${botInfoModel.reviews} ${context.resources.getQuantityString(R.plurals.ratings, botInfoModel.reviews.toInt())}"
        installCount.text = botInfoModel.installs.toString()
        themeNumber.text = botInfoModel.themes.toString()
        phrasesCount.text = botInfoModel.phrases.toString()
        neuroBotDescription.text = botInfoModel.botInfoDescription
        bot_date_added.text = context.getString(R.string.neuro_bot_info_date_adedd_placeholder, botInfoModel.dateAdded)
        bot_date_refreshed.text = context.getString(R.string.neuro_bot_info_date_updated_placeholder, botInfoModel.dateRefreshed)
        bot_type.setText(botInfoModel.type.contentType.label)
        neuroBotAvatar.setImageResource(botInfoModel.avatar)
        addNeuroBot.state = botInfoModel.state

        if (botInfoModel.ownRating == 0) {
            rating.setIsIndicator(false)
            rating.rating = 0f
            rating.setOnRatingChangeListener { ratingBar, ratingValue ->
                ratingBar.rating = ratingValue
                ratingBar.setIsIndicator(true)
                delegate.onRatingChosen(botInfoModel.type, ratingValue.toInt())
            }
        } else {
            rating.setIsIndicator(true)
            rating.rating = botInfoModel.ownRating.toFloat()
        }

        tags_container.removeAllViews()
        val margin = context.pxToDp(16)
        botInfoModel.type.tags.forEach { tag ->
            val tagTextView = TextView(context)
            tagTextView.setText(tag.res)
            tagTextView.setBackgroundResource(R.drawable.bg_tag)
            tags_container.addView(tagTextView)
            val params = tagTextView.layoutParams as FlexboxLayout.LayoutParams
            params.setMargins(margin, margin, margin, margin)
            tagTextView.layoutParams = params
        }
    }

    private fun mapToBotInfo(shopItem: ShopItem): BotInfoModel {
        val neuroBotType = shopItem.smartBotType
        return BotInfoModel(
            botName = context.getString(neuroBotType.title),
            botInfoDescription = context.getString(neuroBotType.description),
            avatar = IconsProvider.rounded(neuroBotType),
            reviews = shopItem.reviews,
            themes = shopItem.themes,
            phrases = shopItem.phrases,
            ownRating = shopItem.ownRating,
            dateAdded = neuroBotType.dateAdded,
            dateRefreshed = neuroBotType.dateUpdated,
            state = shopItem.state,
            averageRating = shopItem.averageRating,
            type = neuroBotType,
            installs = shopItem.installs

        )
    }
}