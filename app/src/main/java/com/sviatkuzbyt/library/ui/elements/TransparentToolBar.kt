package com.sviatkuzbyt.library.ui.elements

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.MaterialColors

class TransparentToolBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr){

    private var isTransparent = true
    private var colorBackgroundTitle = ""
    private val transparent = context.getColor(android.R.color.transparent)
    private val color = MaterialColors.getColor(context, android.R.attr.windowBackground, Color.BLACK)

    init { setStatusBarHeight() }

    private fun setStatusBarHeight() = ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.setPadding(0, insets.top, 0, 0)
        WindowInsetsCompat.CONSUMED
    }

    fun setTitleForColorBackground(text: String){
        colorBackgroundTitle = text
    }

    fun setTransparentBackground(){
        title = ""
        isTransparent = true
        changeColor(color, transparent)
    }

    fun setColorBackground(){
        title = colorBackgroundTitle
        isTransparent = false
        changeColor(transparent, color)
    }

    private fun changeColor(start: Int, end: Int){
        val colorAnimator = ObjectAnimator.ofObject(
            this,
            "backgroundColor",
            ArgbEvaluator(),
            start,
            end
        )

        colorAnimator.duration = 500
        colorAnimator.start()
    }

    fun getIsTransparent() = isTransparent
}