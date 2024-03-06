package dev.android.assignment.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.android.assignment.R

// Set of Material typography styles to start with
private val regularFont = Font(R.font.poppins_regular, FontWeight.Normal)
private val semiBoldFont = Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
private val boldFont = Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
private val fontFamily = FontFamily(regularFont, semiBoldFont, boldFont)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
//     Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)