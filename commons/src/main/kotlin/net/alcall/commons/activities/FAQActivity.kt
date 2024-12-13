package net.alcall.commons.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import kotlinx.collections.immutable.toImmutableList
import net.alcall.commons.compose.extensions.enableEdgeToEdgeSimple
import net.alcall.commons.compose.screens.FAQScreen
import net.alcall.commons.compose.theme.AppThemeSurface
import net.alcall.commons.helpers.APP_FAQ
import net.alcall.commons.models.FAQItem

class FAQActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdgeSimple()
        setContent {
            AppThemeSurface {
                val faqItems = remember { intent.getSerializableExtra(APP_FAQ) as ArrayList<FAQItem> }
                FAQScreen(
                    goBack = ::finish,
                    faqItems = faqItems.toImmutableList()
                )
            }
        }
    }
}
