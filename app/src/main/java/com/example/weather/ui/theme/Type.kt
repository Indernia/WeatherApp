package com.example.weather.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weather.R

val juaFontFamily = FontFamily(
    Font(R.font.jua_regular)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = juaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,  // Small text size
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = juaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,  // Medium text size
        lineHeight = 26.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = juaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 35.sp,  // Larger title text size
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    )
)