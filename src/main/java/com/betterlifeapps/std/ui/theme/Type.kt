package com.betterlifeapps.std.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.betterlifeapps.std.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val lato = FontFamily(
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_medium, FontWeight.Medium),
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_semibold, FontWeight.SemiBold),
    Font(R.font.lato_light, FontWeight.Light)
)

val rubik = FontFamily(
    Font(R.font.rubik_black, FontWeight.Black),
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_medium, FontWeight.Medium),
    Font(R.font.rubik_regular, FontWeight.Normal),
    Font(R.font.rubik_light, FontWeight.Light)
)