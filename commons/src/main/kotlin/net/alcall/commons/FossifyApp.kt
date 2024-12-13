package net.alcall.commons

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import net.alcall.commons.extensions.appLockManager
import net.alcall.commons.extensions.checkUseEnglish

open class FossifyApp : Application() {

    open val isAppLockFeatureAvailable = false

    override fun onCreate() {
        super.onCreate()
        checkUseEnglish()
        setupAppLockManager()
    }

    private fun setupAppLockManager() {
        if (isAppLockFeatureAvailable) {
            ProcessLifecycleOwner.get().lifecycle.addObserver(appLockManager)
        }
    }
}
