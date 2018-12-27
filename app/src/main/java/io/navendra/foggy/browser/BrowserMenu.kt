package io.navendra.foggy.browser

import mozilla.components.browser.menu.BrowserMenuBuilder
import mozilla.components.feature.session.SessionUseCases

class BrowserMenu(sessionUseCases: SessionUseCases){
    val builder = BrowserMenuBuilder(listOf(BrowserToolbar(sessionUseCases).menuItems))
}