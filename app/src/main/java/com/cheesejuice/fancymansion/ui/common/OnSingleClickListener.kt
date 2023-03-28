package com.cheesejuice.fancymansion.ui.common

import android.os.SystemClock

class OnSingleClickListener(
    private val interval : Int = 600,
    private val onSingleClick: () -> Unit
) : ()->Unit {

    private var lastClickTime: Long = 0

    override fun invoke() {
        val elapsedRealtime = SystemClock.elapsedRealtime()
        if ((elapsedRealtime - lastClickTime) < interval) {
            return
        }
        lastClickTime = elapsedRealtime
        onSingleClick()
    }
}