package com.betterlifeapps.std

import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.betterlifeapps.std.common.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

    protected fun showShortToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    protected fun showShortToast(@StringRes textRes: Int) {
        Toast.makeText(requireContext(), textRes, Toast.LENGTH_SHORT).show()
    }

    protected fun showLongToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }

    protected fun showLongToast(@StringRes textRes: Int) {
        Toast.makeText(requireContext(), textRes, Toast.LENGTH_LONG).show()
    }

    @CallSuper
    open fun onUiEvent(event: UiEvent) {
        when (event) {
            is UiEvent.ShowShortToast -> {
                showShortToast(event.text)
            }
            is UiEvent.ShowShortToastRes -> {
                showShortToast(event.textRes)
            }
        }
    }

    /**
     * Terminal flow operator that launches the collection of the given flow with a provided [action]
     */
    protected inline fun <T> Flow<T>.collectFlow(
        crossinline action: suspend (value: T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collect { action(it) }
            }
        }
    }
}