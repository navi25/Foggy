package io.navendra.browser.core

import android.content.Context
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import io.navendra.browser.engine.BrowserWebEngine
import io.navendra.browser.ui.SimpleBrowserView
import kotlinx.android.synthetic.main.browser_view.view.*
import mozilla.components.browser.session.Session
import mozilla.components.browser.session.SessionManager
import mozilla.components.concept.engine.Engine
import mozilla.components.feature.session.SessionFeature
import mozilla.components.feature.session.SessionUseCases
import mozilla.components.feature.toolbar.ToolbarFeature

class BrowserManager(context: Context, lifecycle: Lifecycle, browserView: SimpleBrowserView){


    private val observer: BrowserLifecycleObserver = BrowserLifecycleObserver()
    private val engine: Engine by lazy { BrowserWebEngine(context).gecko  }

    private val toolbarFeature: ToolbarFeature
    private val sessionFeature: SessionFeature
    private val sessionUseCases: SessionUseCases
    private val sessionManager: SessionManager

    private val HOME_URL = "https://www.google.com/"

    init {
        lifecycle.addObserver(observer)
        sessionManager = SessionManager(engine)
        sessionUseCases = SessionUseCases(sessionManager)

        sessionFeature = SessionFeature(
            sessionManager,
            sessionUseCases,
            browserView.conceptEngineView
        )
        toolbarFeature = ToolbarFeature(
            browserView.toolbar,
            sessionManager,
            sessionUseCases.loadUrl
        )

        browserView.toolbar.setMenuBuilder(BrowserMenu(sessionUseCases).builder)

        //Adding a default session
        sessionManager.add(Session(HOME_URL))


    }


    inner class BrowserLifecycleObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun handleOnStart() {
            toolbarFeature.start()
            sessionFeature.start()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun handleOnStop() {
            toolbarFeature.stop()
            sessionFeature.stop()
        }
    }








}