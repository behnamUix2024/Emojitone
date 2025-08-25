package com.behnamuix.emojitone.model

import android.content.Context
import android.content.Intent
import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
    val name: String,
    val category: String,
    val group: String,
    val htmlCode: List<String>,
    val unicode: List<String>
)
fun convertUnicodeToEmoji(unicode: String): String{
   try {
       var hexCode=unicode.drop(2)
       var intCode=hexCode.toInt(16)
       return String(Character.toChars(intCode))
   }catch (e: Exception){
       return ""
   }
    
}
fun shareToSocial(emoji: Emoji?, ctx: Context) {
    var intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, convertUnicodeToEmoji(emoji?.unicode?.get(0) ?: "NULL"))
        type = "text/plain"
    }
    ctx.startActivity(intent)
}
