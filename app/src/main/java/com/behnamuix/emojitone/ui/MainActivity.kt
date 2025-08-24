package com.behnamuix.emojitone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import com.behnamuix.emojitone.ui.theme.EmojitoneTheme
import com.behnamuix.emojitone.View.nav.AllEmojis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var listColor = listOf<Color>(
            Color(0xff3A1C71),
            Color(0xffD76D77),
            Color(0xffFFAF7B)
        )
        setupSystemBars()

        setContent {
            EmojitoneTheme {
                // پس‌زمینه رنگی برای تست شفافیت
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(
                                listColor
                            )
                        ) // پس‌زمینه تیره برای تست
                ) {
                    Navigator(AllEmojis)
                }
            }
        }
    }

    private fun setupSystemBars() {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // نوارها کاملاً شفاف
        window.apply {
            statusBarColor = Color.Transparent.toArgb()
            navigationBarColor = Color.Transparent.toArgb()
        }

        // آیکون‌ها تیره (برای پس‌زمینه روشن بگذار true، برای پس‌زمینه تیره بگذار false)
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = false // آیکون‌های روشن برای پس‌زمینه تیره
        insetsController.isAppearanceLightNavigationBars = false
    }
}
