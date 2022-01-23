package com.betterlifeapps.std.common

import android.content.Context
import com.betterlifeapps.std.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseDialogManager constructor(private val context: Context) {
    fun showConfirmationDialog(
        title: String,
        message: String,
        onPositiveButtonClicked: () -> Unit = {}
    ) {
        MaterialAlertDialogBuilder(context).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(R.string.dialog_ok) { _, _ -> onPositiveButtonClicked() }
            setNegativeButton(R.string.dialog_cancel, null)
        }.show()
    }
}