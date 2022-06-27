package com.betterlifeapps.std.common

import android.content.Context
import com.betterlifeapps.std.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogUtil {
    fun showConfirmationDialog(
        title: String,
        message: String,
        context: Context,
        onPositiveButtonClicked: () -> Unit = {}
    ) {
        MaterialAlertDialogBuilder(context).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(R.string.confirm) { _, _ -> onPositiveButtonClicked() }
            setNegativeButton(R.string.dialog_cancel, null)
        }.show()
    }
}