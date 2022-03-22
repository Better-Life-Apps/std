package com.betterlifeapps.std.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.betterlifeapps.std.ui.theme.toolbarTextStyle

@Composable
fun UiToolbar(
    text: String = "",
    showBackButton: Boolean = true,
    onBackButtonClick: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Color.White
    ) {
        if (showBackButton) {
            IconButton(onClick = {
                onBackButtonClick()
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        } else {
            HSpacer(width = 32)
        }
        Text(
            text = text,
            style = toolbarTextStyle,
            modifier = Modifier.padding(start = 8.dp)
        )
//        Spacer(Modifier.weight(1f, true))
//        IconButton(onClick = {
//            onHistoryClicked()
//        }) {
//            Icon(Icons.Filled.List, contentDescription = null)
//        }
//        IconButton(onClick = {
//            onAboutClicked()
//        }) { Icon(Icons.Filled.Info, contentDescription = null) }
//        IconButton(onClick = { onShareClicked(satiety) }) {
//            Icon(
//                Icons.Filled.Share,
//                contentDescription = null
//            )
//        }
    }
}