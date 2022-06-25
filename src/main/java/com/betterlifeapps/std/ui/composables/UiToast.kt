package com.betterlifeapps.std.ui.composables

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ToastShort(text: String) {
    Toast.makeText(LocalContext.current, text, Toast.LENGTH_SHORT).show()
}

@Composable
fun ToastLong(text: String) {
    Toast.makeText(LocalContext.current, text, Toast.LENGTH_LONG).show()
}