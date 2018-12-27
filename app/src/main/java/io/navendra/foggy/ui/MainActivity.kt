package io.navendra.foggy.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import io.navendra.foggy.App
import io.navendra.foggy.R
import kotlinx.android.synthetic.main.activity_main.*
import mozilla.components.browser.session.Session
import mozilla.components.browser.session.SessionManager
import mozilla.components.concept.engine.Engine
import mozilla.components.concept.engine.EngineSession
import mozilla.components.concept.engine.EngineView
import mozilla.components.feature.session.SessionFeature
import mozilla.components.feature.session.SessionUseCases
import mozilla.components.feature.toolbar.ToolbarFeature

class MainActivity : AppCompatActivity() {

    private val engine: Engine by lazy { (application as App).engine  }

    private lateinit var session: EngineSession
    private lateinit var toolbarFeature: ToolbarFeature
    private lateinit var sessionFeature: SessionFeature
    private lateinit var sessionUseCases: SessionUseCases
    private lateinit var sessionManager: SessionManager

    private val HOME_URL = "https://www.google.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(engine)
        sessionUseCases = SessionUseCases(sessionManager)

        sessionFeature = SessionFeature(
            sessionManager,
            sessionUseCases,
            conceptEngineView
        )
        toolbarFeature = ToolbarFeature(
            toolbar,
            sessionManager,
            sessionUseCases.loadUrl
        )

        //Adding a default session
        sessionManager.add(Session("https://www.mozilla.org"))
    }

    override fun onStart() {
        super.onStart()
        toolbarFeature.start()
        sessionFeature.start()
    }
    override fun onStop() {
        super.onStop()
        toolbarFeature.stop()
        sessionFeature.stop()
    }

    override fun onCreateView(name: String?, context: Context?,
                              attrs: AttributeSet?): View? = when(name){
            EngineView::class.java.name-> engine.createView(context!!,attrs!!).asView()
            else -> super.onCreateView(name, context, attrs)
    }






}
