package net.alcall.commons.compose.extensions

import android.app.Activity
import android.content.Context
import net.alcall.commons.R
import net.alcall.commons.extensions.baseConfig
import net.alcall.commons.extensions.redirectToRateUs
import net.alcall.commons.extensions.toast
import net.alcall.commons.helpers.BaseConfig

val Context.config: BaseConfig get() = BaseConfig.newInstance(applicationContext)

fun Activity.rateStarsRedirectAndThankYou(stars: Int) {
    if (stars == 5) {
        redirectToRateUs()
    }
    toast(R.string.thank_you)
    baseConfig.wasAppRated = true
}
