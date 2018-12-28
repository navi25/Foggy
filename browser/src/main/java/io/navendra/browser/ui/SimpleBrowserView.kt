package io.navendra.browser.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import io.navendra.browser.R

class SimpleBrowserView (
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context,attrs,defStyleAttr) {


    init {
        LayoutInflater.from(context).inflate(R.layout.browser_view, this)
    }


}