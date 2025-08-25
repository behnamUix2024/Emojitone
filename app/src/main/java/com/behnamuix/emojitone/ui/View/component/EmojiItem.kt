package com.behnamuix.emojitone.View.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.behnamuix.emojitone.model.shareToSocial
import com.behnamuix.emojitone.model.Emoji
import com.behnamuix.emojitone.model.convertUnicodeToEmoji

@Composable
fun EmojiCardItem(emoji: Emoji?) {
    var ctx = LocalContext.current
    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEB3B).copy(alpha = 0.1f)),
        modifier = Modifier


            .padding(8.dp)
            .size(90.dp)
            .clickable(onClick = {
                shareToSocial(emoji, ctx)
            })
    ) {
        // لایه اول: پس‌زمینه با افکت بلور (گرادیانت نیمه شفاف)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0x22000000), // آبی نیمه شفاف
                            Color(0x258B5CF6)  // بنفش نیمه شفاف
                        )
                    )
                )

        ){
            Box(){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier

                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    Text(

                        text = convertUnicodeToEmoji(emoji?.unicode?.get(0) ?: ""),
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        text = emoji?.name ?: "",
                        fontSize = 14.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    )

                }
            }
        }


    }


}



