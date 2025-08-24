package com.behnamuix.emojitone.model

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
