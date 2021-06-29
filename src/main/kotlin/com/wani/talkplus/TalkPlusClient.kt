package com.wani.talkplus

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.wani.talkplus.exception.TalkPlusResponseException
import com.wani.talkplus.request.body.user.CreateUserRequestBody
import com.wani.talkplus.response.user.CreateUserResponse
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import java.io.IOException
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.jvm.Throws

class TalkPlusClient(
    private val appId: String,
    private val appKey: String
) {
    companion object {
        private const val API_URL = "https://api.talkplus.io"
        private const val APP_VERSION = "/v1.3"
        private const val API = "/api"
        private const val URL_END = "/"
        const val FULL_API_URL = API_URL + APP_VERSION + API + URL_END

    }

    private val talkPlus: TalkPlus

    init {
        val client = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL(FULL_API_URL))
            .addConverterFactory(buildGsonConverter())
            .client(client)
            .build()


        talkPlus = retrofit.create(TalkPlus::class.java)
    }

    @Throws(TalkPlusResponseException::class, IOException::class)
    fun createUser(contentType: String?, request: CreateUserRequestBody): CreateUserResponse? {
        val call = talkPlus.createUser(
            contentType = contentType ?: "",
            apiKey = appKey,
            appId = appId,
            request = request
        )

        val response = call.execute()

        if (!response.isSuccessful) {
            throw TalkPlusResponseException(getExceptionMessage(response), HttpException(response))
        }

        return response.body()
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