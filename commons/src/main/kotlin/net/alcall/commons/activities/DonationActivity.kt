package net.alcall.commons.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import net.alcall.commons.R
import net.alcall.commons.compose.extensions.enableEdgeToEdgeSimple
import net.alcall.commons.compose.screens.DonationScreen
import net.alcall.commons.compose.theme.AppThemeSurface
import net.alcall.commons.extensions.openWebsiteIntent
import net.alcall.commons.extensions.toast
import net.alcall.commons.models.Donation

class DonationActivity : BaseComposeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdgeSimple()
        setContent {
            val clipboardManager = LocalClipboardManager.current
            AppThemeSurface {
                DonationScreen(
                    donationOptions = FossifyDonationPlatforms,
                    cryptoAddresses = FossifyCryptoAddresses,
                    goBack = ::finish,
                    openWebsite = ::openWebsiteIntent,
                    copyToClipboard = {
                        clipboardManager.setText(AnnotatedString(it))
                        toast(R.string.value_copied_to_clipboard)
                    },
                )
            }
        }
    }
}

internal val FossifyDonationPlatforms = listOf(
    Donation.Platform(
        fee = 0,
        link = "https://github.com/sponsors/FossifyOrg",
        nameRes = R.string.github_sponsors,
        iconRes = R.drawable.ic_github_tinted_vector
    ),
    Donation.Platform(
        fee = 0,
        link = "https://liberapay.com/naveensingh",
        nameRes = R.string.liberapay,
        iconRes = R.drawable.ic_liberapay_vector
    ),
    Donation.Platform(
        fee = 10,
        link = "https://opencollective.com/fossify/donate?interval=month&amount=20",
        nameRes = R.string.opencollective,
        iconRes = R.drawable.ic_open_collective_vector
    ),
    Donation.Platform(
        fee = 10,
        link = "https://www.patreon.com/naveen3singh",
        nameRes = R.string.patreon,
        iconRes = R.drawable.ic_patreon_vector
    ),
    Donation.Platform(
        fee = 5,
        link = "https://paypal.me/naveen3singh",
        nameRes = R.string.paypal,
        iconRes = R.drawable.ic_paypal_vector
    ),
)

@Suppress("SpellCheckingInspection")
internal val FossifyCryptoAddresses = listOf(
    Donation.Crypto(
        address = "bc1qn5h97qdqsazpzvxm7gryke6vmrcx85t7neqp95",
        iconRes = R.drawable.ic_bitcoin_vector,
        nameRes = R.string.bitcoin_btc
    ),
    Donation.Crypto(
        address = "0x9354fC372BC3BdA58766a8a9Fabadf77A76CdE01",
        iconRes = R.drawable.ic_ethereum_vector,
        nameRes = R.string.ethereum_eth
    ),
    Donation.Crypto(
        address = "48FkVUcJ7AGeBMR4SC4J7QU5nAt6YNwKZWz6sGDT1s5haEY7reZtJr5CniXLaQzTzGAuZNoc83BQAcETHw1d3Lkn8AAf1XF",
        iconRes = R.drawable.ic_monero_vector,
        nameRes = R.string.monero_xmr
    ),
    Donation.Crypto(
        address = "TGi4VpD1D9A9ZvyP9d3aVowwzMSvev2hub",
        iconRes = R.drawable.ic_tron_vector,
        nameRes = R.string.tron_trx
    )
)
