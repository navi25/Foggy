package io.navendra.foggy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.navendra.browser.core.BrowserManager
import io.navendra.browser.ui.SimpleBrowserView


class MainActivity : AppCompatActivity() {
    
    private lateinit var browserManager: BrowserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val browserView = SimpleBrowserView(this)
        browserManager = BrowserManager(applicationContext,lifecycle, browserView)

        setContentView(browserView)
    }



}
