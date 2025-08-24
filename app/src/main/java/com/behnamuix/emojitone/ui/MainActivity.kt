package com.behnamuix.emojitone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import com.behnamuix.emojitone.ui.theme.EmojitoneTheme
import com.behnamuix.emojitone.View.nav.AllEmojis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // تنظیمات پنجره و نوارهای سیستم
        setupSystemBars()

        setContent {
            EmojitoneTheme {
                Navigator(AllEmojis)
            }
        }
    }

    private fun setupSystemBars() {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // تنظیم رنگ نوارها
        window.apply {
            statusBarColor = Color.Black.toArgb()
            navigationBarColor = Color.Black.toArgb()
        }

        // تنظیم رنگ آیکون‌های نوار ناوبری (روشن یا تیره)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false // آیکون‌های status bar تیره
            isAppearanceLightNavigationBars = false // آیکون‌های navigation bar تیره
        }
    }
}