package com.wani.talkplus

import com.google.gson.annotations.SerializedName
import com.wani.talkplus.request.body.user.CreateUserRequestBody
import com.wani.talkplus.request.header.user.CreateUserRequestHeader
import com.wani.talkplus.response.user.CreateUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TalkPlus {

    @POST("users/create")
    fun createUser(
        @Header("content-type")
        contentType: String? = "application/json",
        @Header("api-key")
        apiKey: String,
        @Header("app-id")
        appId: String,
        @Body request: CreateUserRequestBody): Call<CreateUserResponse>

}