package net.alcall.commons.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import net.alcall.commons.extensions.syncGlobalConfig
import net.alcall.commons.helpers.MyContentProvider

class FossifyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == MyContentProvider.ACTION_GLOBAL_CONFIG_UPDATED) {
            context?.syncGlobalConfig()
        }
    }
}
