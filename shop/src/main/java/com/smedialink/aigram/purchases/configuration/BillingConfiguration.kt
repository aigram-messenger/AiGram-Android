package com.smedialink.aigram.purchases.configuration

import org.solovyev.android.checkout.Billing
import org.solovyev.android.checkout.PurchaseVerifier

/**
 * Created by Oleg Sheliakin on 14.01.2019.
 * Contact me by email - olegsheliakin@gmail.com
 */
class BillingConfiguration : Billing.DefaultConfiguration() {
    override fun getPublicKey(): String {
        val s = "" // TODO Your billing key here
        return s
    }

    override fun getPurchaseVerifier(): PurchaseVerifier {
        return super.getPurchaseVerifier()
    }
}