package com.anaumchik.buildyourbody.data.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.anaumchik.buildyourbody.R
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

fun View.string(@StringRes stringRes: Int): String = this.context.getString(stringRes)
fun Context.string(@StringRes stringRes: Int): String = this.getString(stringRes)
fun Fragment.string(@StringRes stringRes: Int): String = this.getString(stringRes)

fun View.toast(@StringRes stringRes: Int) =
    Toast.makeText(this.context, this.string(stringRes), Toast.LENGTH_SHORT).show()

fun Context.color(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

fun Fragment.routeTo(@IdRes destinationRes: Int) =
    requireActivity().findNavController(R.id.nav_host).navigate(destinationRes)

fun Fragment.bottomNavMenuRouteTo(@IdRes destinationRes: Int): Boolean {
    requireActivity().findNavController(R.id.nav_host).navigate(destinationRes)
    return true
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(this.context).inflate(layoutRes, this, false)

fun BottomNavigationView.deselectItems() {
    menu.getItem(0).isCheckable = false
    menu.getItem(1).isCheckable = false
    menu.getItem(2).isCheckable = false
}

fun Fragment.toolbarTitle(@StringRes stringRes: Int) {
    requireActivity().toolbar.title = string(stringRes)
}

fun Fragment.enableToolbarBackButton(enable: Boolean) {
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(enable)
}

fun ImageView.loadDrawable(@DrawableRes drawableRes: Int) = Glide
    .with(this.context)
    .load(drawableRes)
    .fitCenter()
    .into(this)

fun View.background(@ColorRes colorRes: Int) {
    this.background = ContextCompat.getDrawable(this.context, colorRes)
}
