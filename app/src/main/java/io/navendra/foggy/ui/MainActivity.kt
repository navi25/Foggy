package io.navendra.foggy.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import io.navendra.foggy.App
import io.navendra.foggy.FoggyEngine
import io.navendra.foggy.R
import kotlinx.android.synthetic.main.activity_main.*
import mozilla.components.browser.engine.gecko.GeckoEngine
import mozilla.components.concept.engine.Engine
import mozilla.components.concept.engine.EngineSession
import mozilla.components.concept.engine.EngineView

class MainActivity : AppCompatActivity() {

    private val engine: Engine by lazy { (application as App).engine  }
    private lateinit var engineView: EngineView
    private lateinit var session: EngineSession

    private val HOME_URL = "https://www.google.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        session = engine.createSession()

        session.loadUrl(HOME_URL)

        engineView = conceptEngineView.apply {
            render(session)
        }
    }

    override fun onCreateView(name: String?, context: Context?,
                              attrs: AttributeSet?): View? = when(name){
            EngineView::class.java.name-> engine.createView(context!!,attrs!!).asView()
            else -> super.onCreateView(name, context, attrs)
    }






}
