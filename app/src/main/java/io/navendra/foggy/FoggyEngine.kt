package io.navendra.foggy

import android.content.Context
import mozilla.components.browser.engine.gecko.GeckoEngine
import mozilla.components.concept.engine.DefaultSettings
import mozilla.components.concept.engine.EngineSession

class FoggyEngine(context: Context){

    private val defaultSettings = DefaultSettings().apply {
        trackingProtectionPolicy = EngineSession.TrackingProtectionPolicy.all()
    }

    // Create an engine instance to be used by other components.
    val gecko by lazy { GeckoEngine(context, defaultSettings)  }



}