package com.app.newsappmvvmjetpack.common

import android.content.Context
import android.text.format.DateUtils
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.core.content.ContextCompat
import com.app.newsappmvvmjetpack.R
import java.text.ParseException
import java.text.SimpleDateFormat

object CommonFunction {

    fun intToColor(colorValue: Int): Color {
        return Color(colorValue)
    }

    fun getColorFromResId(context: Context, resId: Int): Color {
        return Color(ContextCompat.getColor(context, resId))
    }

    fun getTimeAgo(date_str: String?): CharSequence {
        return if (date_str != null && date_str.trim { it <= ' ' } != "") {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            //sdf.setTimeZone(TimeZone.getTimeZone("CET"));
            try {
                val time = sdf.parse(date_str).time
                val now = System.currentTimeMillis()
                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
            } catch (e: ParseException) {
                ""
            }
        } else {
            ""
        }
    }

    val poppinsRegular = FontFamily(
        Font(R.font.poppinsregular)
    )
    val poppinsBlack = FontFamily(
        Font(R.font.poppinsblack)
    )
    val poppinsSemibold = FontFamily(
        Font(R.font.poppinssemibold)
    )
    val poppinsBold = FontFamily(
        Font(R.font.poppinsbold)
    )
    val poppinsBoldItalic = FontFamily(
        Font(R.font.poppinsbolditalic)
    )
}