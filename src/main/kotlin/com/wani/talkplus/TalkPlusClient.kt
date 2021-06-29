package com.wani.talkplus

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class TalkPlusClient(
    val appId: String,
    val appKey: String
) {
    companion object {
        private const val API_URL = "https://api.talkplus.io"
        private const val APP_VERSION = "/v.13"
        private const val API = "/api"
        const val FULL_API_URL = API_URL + APP_VERSION + API

    }

    init {
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL + APP_VERSION + FULL_API_URL)
            .addConverterFactory(buildGsonConverter())
            .client(client)
            .build()

        retrofit.create(this::class.java)
    }

    private fun buildGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()

        val gson = gsonBuilder.create()

        return GsonConverterFactory.create(gson)
    }

    private fun getExceptionMessage(response: Response<*>): String =
        try {
            val element = JsonParser().parse(response.errorBody()?.string())
            element.asJsonObject.get("message").asString
        } catch (e: JsonSyntaxException) {
            e.message.toString()
        } catch (e: IOException) {
            e.message.toString()
        }

}