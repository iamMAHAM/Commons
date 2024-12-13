package net.alcall.commons.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import net.alcall.commons.R
import net.alcall.commons.activities.BaseSimpleActivity
import net.alcall.commons.compose.alert_dialog.*
import net.alcall.commons.compose.extensions.MyDevices
import net.alcall.commons.compose.theme.AppThemeSurface
import net.alcall.commons.databinding.DialogOpenDeviceSettingsBinding
import net.alcall.commons.extensions.getAlertDialogBuilder
import net.alcall.commons.extensions.openDeviceSettings
import net.alcall.commons.extensions.setupDialogStuff

class OpenDeviceSettingsDialog(val activity: BaseSimpleActivity, message: String) {

    init {
        activity.apply {
            val view = DialogOpenDeviceSettingsBinding.inflate(layoutInflater, null, false)
            view.openDeviceSettings.text = message
            getAlertDialogBuilder()
                .setNegativeButton(R.string.close, null)
                .setPositiveButton(R.string.go_to_settings) { _, _ ->
                    openDeviceSettings()
                }.apply {
                    setupDialogStuff(view.root, this)
                }
        }
    }
}

@Composable
fun OpenDeviceSettingsAlertDialog(
    alertDialogState: AlertDialogState,
    message: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AlertDialog(
        containerColor = dialogContainerColor,
        modifier = modifier
            .dialogBorder,
        onDismissRequest = alertDialogState::hide,
        shape = dialogShape,
        tonalElevation = dialogElevation,
        text = {
            Text(
                fontSize = 16.sp,
                text = message,
                color = dialogTextColor
            )
        },
        dismissButton = {
            TextButton(onClick = alertDialogState::hide) {
                Text(text = stringResource(id = R.string.close))
            }
        },
        confirmButton = {
            TextButton(onClick = {
                context.openDeviceSettings()
                alertDialogState.hide()
            }) {
                Text(text = stringResource(id = R.string.go_to_settings))
            }
        },
    )
}

@Composable
@MyDevices
private fun OpenDeviceSettingsAlertDialogPreview() {
    AppThemeSurface {
        OpenDeviceSettingsAlertDialog(
            alertDialogState = rememberAlertDialogState(),
            message = "Test dialog"
        )
    }
}
