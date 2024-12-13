package net.alcall.commons.samples.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import net.alcall.commons.activities.BaseSimpleActivity
import net.alcall.commons.activities.ManageBlockedNumbersActivity
import net.alcall.commons.compose.alert_dialog.AlertDialogState
import net.alcall.commons.compose.alert_dialog.rememberAlertDialogState
import net.alcall.commons.compose.extensions.*
import net.alcall.commons.compose.theme.AppThemeSurface
import net.alcall.commons.dialogs.ConfirmationDialog
import net.alcall.commons.dialogs.DonateAlertDialog
import net.alcall.commons.dialogs.RateStarsAlertDialog
import net.alcall.commons.extensions.appLaunched
import net.alcall.commons.extensions.launchMoreAppsFromUsIntent
import net.alcall.commons.extensions.launchViewIntent
import net.alcall.commons.helpers.LICENSE_AUTOFITTEXTVIEW
import net.alcall.commons.models.FAQItem
import net.alcall.commons.samples.BuildConfig
import net.alcall.commons.samples.R
import net.alcall.commons.samples.screens.MainScreen

class MainActivity : BaseSimpleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appLaunched(BuildConfig.APPLICATION_ID)
        enableEdgeToEdgeSimple()
        setContent {
            AppThemeSurface {
                val showMoreApps = onEventValue { !resources.getBoolean(net.alcall.commons.R.bool.hide_google_relations) }

                MainScreen(
                    openColorCustomization = ::startCustomizationActivity,
                    manageBlockedNumbers = {
                        startActivity(Intent(this@MainActivity, ManageBlockedNumbersActivity::class.java))
                    },
                    showComposeDialogs = {
                        startActivity(Intent(this@MainActivity, TestDialogActivity::class.java))
                    },
                    openTestButton = {
                        ConfirmationDialog(
                            this@MainActivity,
                            FAKE_VERSION_APP_LABEL,
                            positive = net.alcall.commons.R.string.ok,
                            negative = 0
                        ) {
                            launchViewIntent(DEVELOPER_PLAY_STORE_URL)
                        }
                    },
                    showMoreApps = showMoreApps,
                    openAbout = ::launchAbout,
                    moreAppsFromUs = ::launchMoreAppsFromUsIntent
                )
                AppLaunched()
            }
        }
    }

    @Composable
    private fun AppLaunched(
        donateAlertDialogState: AlertDialogState = getDonateAlertDialogState(),
        rateStarsAlertDialogState: AlertDialogState = getRateStarsAlertDialogState(),
    ) {
        LaunchedEffect(Unit) {
            appLaunchedCompose(
                appId = BuildConfig.APPLICATION_ID,
                showDonateDialog = donateAlertDialogState::show,
                showRateUsDialog = rateStarsAlertDialogState::show,
                showUpgradeDialog = {}
            )
        }
    }

    @Composable
    private fun getDonateAlertDialogState() =
        rememberAlertDialogState().apply {
            DialogMember {
                DonateAlertDialog(alertDialogState = this)
            }
        }

    @Composable
    private fun getRateStarsAlertDialogState() = rememberAlertDialogState().apply {
        DialogMember {
            RateStarsAlertDialog(alertDialogState = this, onRating = ::rateStarsRedirectAndThankYou)
        }
    }

    private fun launchAbout() {
        val licenses = LICENSE_AUTOFITTEXTVIEW

        val faqItems = arrayListOf(
            FAQItem(net.alcall.commons.R.string.faq_1_title_commons, net.alcall.commons.R.string.faq_1_text_commons),
            FAQItem(net.alcall.commons.R.string.faq_1_title_commons, net.alcall.commons.R.string.faq_1_text_commons),
            FAQItem(net.alcall.commons.R.string.faq_4_title_commons, net.alcall.commons.R.string.faq_4_text_commons)
        )

        if (!resources.getBoolean(net.alcall.commons.R.bool.hide_google_relations)) {
            faqItems.add(FAQItem(net.alcall.commons.R.string.faq_2_title_commons, net.alcall.commons.R.string.faq_2_text_commons))
            faqItems.add(FAQItem(net.alcall.commons.R.string.faq_6_title_commons, net.alcall.commons.R.string.faq_6_text_commons))
        }

        startAboutActivity(
            appNameId = R.string.commons_app_name,
            licenseMask = licenses,
            versionName = BuildConfig.VERSION_NAME,
            faqItems = faqItems,
            showFAQBeforeMail = true,
        )
    }

    override fun getAppLauncherName() = getString(R.string.commons_app_name)

    override fun getAppIconIDs() = arrayListOf(
        R.mipmap.ic_launcher_red,
        R.mipmap.ic_launcher_pink,
        R.mipmap.ic_launcher_purple,
        R.mipmap.ic_launcher_deep_purple,
        R.mipmap.ic_launcher_indigo,
        R.mipmap.ic_launcher_blue,
        R.mipmap.ic_launcher_light_blue,
        R.mipmap.ic_launcher_cyan,
        R.mipmap.ic_launcher_teal,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher_light_green,
        R.mipmap.ic_launcher_lime,
        R.mipmap.ic_launcher_yellow,
        R.mipmap.ic_launcher_amber,
        R.mipmap.ic_launcher_orange,
        R.mipmap.ic_launcher_deep_orange,
        R.mipmap.ic_launcher_brown,
        R.mipmap.ic_launcher_blue_grey,
        R.mipmap.ic_launcher_grey_black
    )

    override fun getRepositoryName() = "General-Discussion"
}
