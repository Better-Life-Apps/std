package com.betterlifeapps.std.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.betterlifeapps.std.R

@Composable
fun UiMenuItem(text: String, onClicked: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClicked() }
            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6
        )
        Image(
            painter = painterResource(id = R.drawable.ic_menu_arrow),
            contentDescription = null
        )
    }
}