package io.navendra.foggy.browser

import io.navendra.foggy.R
import mozilla.components.browser.menu.item.BrowserMenuItemToolbar
import mozilla.components.feature.session.SessionUseCases

class BrowserToolbar(sessionUseCases: SessionUseCases){

    private val back = BrowserMenuItemToolbar.Button(
        mozilla.components.ui.icons.R.drawable.mozac_ic_back,
        iconTintColorResource = R.color.photonBlue90,
        contentDescription = "Back") {
        sessionUseCases.goBack.invoke()
    }

    private val refresh = BrowserMenuItemToolbar.Button(
        mozilla.components.ui.icons.R.drawable.mozac_ic_refresh,
        iconTintColorResource = R.color.photonBlue90,
        contentDescription = "Refresh") {
        sessionUseCases.reload.invoke()
    }

    private val stop = BrowserMenuItemToolbar.Button(
        mozilla.components.ui.icons.R.drawable.mozac_ic_stop,
        iconTintColorResource = R.color.photonBlue90,
        contentDescription = "Stop") {
        sessionUseCases.stopLoading.invoke()
    }


    val menuItems = BrowserMenuItemToolbar(listOf(back,refresh,stop))
}