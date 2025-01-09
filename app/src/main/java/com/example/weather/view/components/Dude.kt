package com.example.weather.view.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import com.example.weather.R

class FigureComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val baseFigure: ImageView
    private val clothing: ImageView
    private val accessory: ImageView

    init {
        baseFigure = ImageView(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            setImageResource(R.drawable.dude)
        }

        clothing = ImageView(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }

        accessory = ImageView(context).apply {
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        }

        addView(baseFigure)
        addView(clothing)
        addView(accessory)
    }

    fun setBaseFigure(@DrawableRes resId: Int) {
        baseFigure.setImageResource(resId)
    }

    fun setClothing(@DrawableRes resId: Int) {
        clothing.setImageResource(resId)
    }

    fun setAccessory(@DrawableRes resId: Int) {
        accessory.setImageResource(resId)
    }
}
