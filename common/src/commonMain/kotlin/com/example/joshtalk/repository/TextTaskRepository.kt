package com.example.joshtalk.repository


import com.example.joshtalk.data.ProductResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class TextTaskRepository {


    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }


    suspend fun fetchTextToRead(): String {
        return try {
            val response: ProductResponse = client
                .get("https://dummyjson.com/products")
                .body()


            response.products.random().description
        } catch (e: Exception) {
            e.printStackTrace()

            "Error loading API. Please read this: The quick brown fox jumps over the lazy dog."
        }
    }
}