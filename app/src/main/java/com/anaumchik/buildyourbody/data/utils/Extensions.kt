package com.anaumchik.buildyourbody.data.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.anaumchik.buildyourbody.R
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

fun Fragment.string(@StringRes stringRes: Int): String = this.getString(stringRes)
fun <T> Fragment.string(@StringRes stringRes: Int, value: T): String = this.getString(stringRes, value)
fun View.string(@StringRes stringRes: Int): String = this.context.getString(stringRes)
fun Context.string(@StringRes stringRes: Int): String = this.getString(stringRes)

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

fun Fragment.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(requireContext()).inflate(layoutRes, null)

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

fun Fragment.alertDialog(
    @LayoutRes layoutRes: Int,
    @IdRes titleIdRes: Int,
    @StringRes titleStringRes: Int = 0,
    title: String? = null,
    @IdRes descriptionIdRes: Int,
    @StringRes descriptionStringRes: Int = 0,
    description: String? = null,
    @IdRes btnIdRes: Int,
    btnAction: () -> Unit
): AlertDialog = AlertDialog.Builder(requireContext()).create().apply {
    val view = inflate(layoutRes)
    setView(view)
    view.findViewById<TextView>(titleIdRes)?.text =
        title?.let { it } ?: run { string(titleStringRes) }
    view.findViewById<TextView>(descriptionIdRes)?.text =
        description?.let { it } ?: run { string(descriptionStringRes) }
    view.findViewById<Button>(btnIdRes)?.setOnClickListener {
        dismiss()
        btnAction.invoke()
    }
    setCancelable(false)
    show()
}
