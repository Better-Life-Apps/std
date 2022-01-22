package com.betterlifeapps.std

import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    protected val supportActionBar: ActionBar?
        get() = (requireActivity() as? AppCompatActivity)?.supportActionBar

    protected fun setupActionBar(actionBarInitializer: ActionBar.() -> Unit) {
        supportActionBar?.apply {
            setHomeAsUpIndicator(null)
            setDisplayHomeAsUpEnabled(false)
            title = ""
            actionBarInitializer()
        }
    }
}