package com.example.weather.view.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.weather.R

class FigureComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val baseFigure: ImageView
    private val clothing: ImageView
    private val accessory: ImageView

    init {

        baseFigure = ImageView(context).apply {
            id = View.generateViewId() // Assign a unique ID to the base figure
            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                startToStart = LayoutParams.PARENT_ID
                topToTop = LayoutParams.PARENT_ID
                setPadding(0, 200, 0, 0)
            }
            setImageResource(R.drawable.dude)
        }

        clothing = ImageView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(700, 270).apply {
                startToStart = baseFigure.id
                bottomToBottom = baseFigure.id
                setMargins(64, 0, 0, 56)
            }
        }

        accessory = ImageView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(350, 270).apply {
                startToStart = baseFigure.id
                topToTop = baseFigure.id
                setMargins(248, 20, 0, 0)
            }
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

