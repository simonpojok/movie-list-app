package org.mdeveloperz.common.ui

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.setVisibility(isVisible: Boolean) {
    if (isVisible) visible() else gone()
}