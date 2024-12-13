package net.alcall.commons.samples

import com.github.ajalt.reprint.core.Reprint
import net.alcall.commons.FossifyApp

class App : FossifyApp() {
    override fun onCreate() {
        super.onCreate()
        Reprint.initialize(this)
    }
}
