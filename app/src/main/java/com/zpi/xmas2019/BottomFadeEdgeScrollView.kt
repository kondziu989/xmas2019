package com.zpi.xmas2019

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.ScrollView

class BottomFadeEdgeScrollView : ScrollView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun getBottomFadingEdgeStrength(): Float {
        return 0.0f
    }
}
