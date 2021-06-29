package com.wani.talkplus.request.header.user

import com.google.gson.annotations.SerializedName

data class CreateUserRequestHeader(
    @SerializedName("content-type")
    val contentType: String? = "application/json",

    @SerializedName("api-key")
    val apiKey: String,

    @SerializedName("app-id")
    val appId: String
)
