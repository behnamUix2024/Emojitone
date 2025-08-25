package com.behnamuix.emojitone.network

import com.behnamuix.emojitone.model.Emoji
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

suspend fun getRandomEmoji(): Emoji{
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json{ignoreUnknownKeys = true} )
        }
    }
    val emoji: Emoji = client.get("https://emojihub.yurace.pro/api/random").body()

    client.close()
    return emoji
}
suspend fun getAllEmojis(): List<Emoji>{
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json{ignoreUnknownKeys = true} )

        }

    }
    val emojis: List<Emoji> = client.get("https://emojihub.yurace.pro/api/all").body()

    client.close()
    return emojis
}