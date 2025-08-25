package com.behnamuix.emojitone.View.nav

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.behnamuix.emojitone.R
import com.behnamuix.emojitone.View.component.EmojiCardItem
import com.behnamuix.emojitone.model.Emoji
import com.behnamuix.emojitone.model.convertUnicodeToEmoji
import com.behnamuix.emojitone.network.getAllEmojis
import com.behnamuix.emojitone.network.getRandomEmoji
import com.behnamuix.emojitone.ui.theme.Byekan
import com.behnamuix.emojitone.ui.translator.translateToEnglish
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch

object RandomEmojiSc : Screen {
    @Composable
    override fun Content() {
        var emoji by remember { mutableStateOf<Emoji?>(null) }
        var scope = rememberCoroutineScope()
        var loaidng by remember { mutableStateOf(false) }
        var nav = LocalNavigator.currentOrThrow
        LaunchedEffect(Unit) {
            emoji = getRandomEmoji()
        }

        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                contentAlignment = Alignment.TopStart
            ) {
                IconButton({ nav.pop() }) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = Color(0xFFFFFFFF)
                    )
                }
            }
            OutlinedCard(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .size(300.dp)
                    .padding()
            ) {

                Column(
                    modifier = Modifier
                        .sizeIn(minHeight = 200.dp, maxHeight = 300.dp)

                        .fillMaxSize()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Crossfade(loaidng) {
                        if (it) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                CircularProgressIndicator()
                            }
                        } else {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = convertUnicodeToEmoji(emoji?.unicode?.get(0) ?: ""),
                                fontSize = 76.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    Text(
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        text = emoji?.name ?: "",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        text = emoji?.group ?: "",
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )
                    Spacer(Modifier.height(20.dp))


                }

            }

        }
    }

}

object AllEmojis : Screen {
    @Composable
    override fun Content() {
        var nav = LocalNavigator.currentOrThrow
        var emojis by remember { mutableStateOf<List<Emoji?>>(emptyList()) }
        var filteredEmojis by remember { mutableStateOf<List<Emoji?>>(emptyList()) }
        var loading by remember { mutableStateOf(true) }
        var text by remember { mutableStateOf("") }
        var textPer by remember { mutableStateOf("") }
        var group by remember { mutableStateOf("") }
        LaunchedEffect(Unit) {
            loading = true
            emojis = getAllEmojis()
            filteredEmojis = emojis
            loading = false
        }
        LaunchedEffect(textPer) {
            filteredEmojis = if (textPer.isNotBlank()) {
                emojis.filter { it?.name?.contains(textPer, ignoreCase = true) == true }
            } else {
                emojis
            }
        }
        LaunchedEffect(text) {
            delay(300) // صبر کن تا کاربر تایپ کند
            if (text.isNotBlank()) {
                textPer = translateToEnglish(text)

            } else {
                text = ""
            }
        }
        Column(
            modifier = Modifier
                .padding(top = 40.dp)

        ) {
           Column(
               Modifier.fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally) {
               Card(

                   shape = RoundedCornerShape(8.dp),
                   colors = CardDefaults.cardColors(
                       containerColor = Color.Black.copy(
                           alpha = 0.2f
                       )
                   )
               ) {
                   Text(
                       fontFamily = Byekan,
                       maxLines = 1,
                       fontSize = 14.sp,
                       text = textPer + " :ترجمه به انگلیسی",
                       textAlign = TextAlign.Center,
                       color = Color.White,
                       modifier = Modifier
                           .fillMaxWidth(0.5f)
                           .padding(4.dp)
                           .clip(shape = RoundedCornerShape(12.dp))
                           .clickable(onClick = { text = textPer })
                   )
               }
           }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton({ nav.push(RandomEmojiSc) }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.icon_random),
                        contentDescription = "",
                        tint = Color(0xFFF44336)
                    )
                }
                OutlinedTextField(
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0x80FFEB3B)),
                    shape = RoundedCornerShape(12.dp),
                    value = text,

                    onValueChange = {
                        text = it

                    },

                    maxLines = 1,
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White,
                        textDirection = TextDirection.Rtl,
                        fontFamily = Byekan
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    placeholder = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            fontFamily = Byekan,
                            text = "جستجو اموجی",
                            color = Color.White.copy(alpha = 0.5f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = Color(0x80FFEB3B)
                        )
                    }

                )
            }
            Row {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                        .height(50.dp)
                ) {
                    Text("Group List:", fontSize = 14.sp, color = Color.White.copy(alpha = 0.5f))
                    LazyColumn(
                        modifier = Modifier
                            .weight(0.5f)
                    ) {
                        items(items = emojis.shuffled()) {
                            Column(
                                Modifier.clickable(onClick = { group = it?.group.toString() })
                            ) {

                                Text(
                                    text = it?.group ?: "",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
                OutlinedTextField(
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color(0x80FFEB3B)),
                    shape = RoundedCornerShape(12.dp),
                    value = group,
                    textStyle = TextStyle(
                        textDirection = TextDirection.Rtl,
                        fontFamily = Byekan,
                        color = Color.White,
                        fontSize = 18.sp
                    ),
                    onValueChange = {
                        group = it

                    },
                    maxLines = 1,

                    modifier = Modifier
                        .weight(0.5f)

                        .padding(12.dp),
                    placeholder = {
                        Text(
                            color = Color.White.copy(alpha = 0.5f),
                            modifier = Modifier.fillMaxWidth(),
                            text = "نام گروه",
                            fontSize = 16.sp,
                            fontFamily = Byekan,
                            style = TextStyle(textDirection = TextDirection.Rtl)
                        )
                    },
                    leadingIcon = {
                        IconButton({
                            filteredEmojis =
                                emojis.filter { (it?.group?.contains(group) ?: "") as Boolean }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "",
                                tint = Color(0x80FFEB3B)
                            )
                        }
                    }

                )
            }
            if (loading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 50.dp, top = 10.dp),
                    columns = GridCells.Fixed(count = 4)
                ) {
                    items(items = filteredEmojis) {
                        EmojiCardItem(it)
                    }
                }
            }
        }
    }

}






