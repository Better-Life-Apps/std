package com.betterlifeapps.std.ui

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.betterlifeapps.std.ui.theme.Grey_Light
import com.betterlifeapps.std.ui.theme.lato

@Composable
fun UiTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.White,
        disabledTextColor = Color.Transparent,
        backgroundColor = Color.Transparent,
        focusedIndicatorColor = Color.Gray,
        unfocusedIndicatorColor = Color.Gray,
        disabledIndicatorColor = Color.Gray
    )
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = lato,
            fontWeight = FontWeight.Normal
        ),
        placeholder = { Text(text = hint, color = Grey_Light) },
        colors = colors,
        trailingIcon = trailingIcon,
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        modifier = modifier
    )
}
