package com.betterlifeapps.std

import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    private var previousSoftInputMode = -1

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

    /**
     * Sets the soft input mode for the current screen.
     * Should be called when the fragment is in the STARTED state, because
     * it gets reset each time the fragment is STOPPED e.g. app minimised.
     */
    protected fun setSoftInputMode(softInputMode: Int) {
        previousSoftInputMode = activity?.window?.attributes?.softInputMode ?: -1
        activity?.window?.setSoftInputMode(softInputMode)
    }

    private fun restoreSoftInputMode() {
        if (previousSoftInputMode > -1) {
            activity?.window?.setSoftInputMode(previousSoftInputMode)
        }
    }

    override fun onStop() {
        super.onStop()
        restoreSoftInputMode()
    }
}