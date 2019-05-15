package com.anaumchik.buildyourbody.data.utils

import android.animation.Animator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.anaumchik.buildyourbody.R

fun Context.string(@StringRes stringRes: Int): String = this.getString(stringRes)

fun Context.color(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

fun TextView.textColor(@ColorRes colorRes: Int) = this.setTextColor(this.context.color(colorRes))

fun Context.toast(@StringRes stringRes: Int) = Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()

fun Fragment.routeTo(@IdRes destinationRes: Int) =
    requireActivity().findNavController(R.id.nav_host).navigate(destinationRes)

fun Fragment.bottomNavMenuRouteTo(@IdRes destinationRes: Int): Boolean {
    requireActivity().findNavController(R.id.nav_host).navigate(destinationRes)
    return true
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(this.context).inflate(layoutRes, this, false)

fun ViewPropertyAnimator.animationEndListener(endAction: () -> Unit): ViewPropertyAnimator =
    this.setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) = endAction.invoke()
    })

