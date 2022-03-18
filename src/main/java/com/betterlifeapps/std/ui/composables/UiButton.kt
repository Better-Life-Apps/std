package com.betterlifeapps.std.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UiButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 32.dp, vertical = 8.dp)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.small.copy(
            CornerSize(16.dp)
        ),
        contentPadding = contentPadding
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = MaterialTheme.colors.onPrimary,
            fontFamily = MaterialTheme.typography.button.fontFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun UiButton(
    @StringRes stringRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 32.dp, vertical = 8.dp)
) = UiButton(text = stringResource(id = stringRes), onClick = onClick, modifier, contentPadding)

@Preview
@Composable
fun PreviewUiButton() {
    UiButton("Button", {})
}