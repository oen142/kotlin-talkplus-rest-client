package com.wani.talkplus.request.body.user

import com.google.gson.annotations.SerializedName

data class CreateUserRequestBody(

    @SerializedName("userId")
    val userId: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("username")
    val username: String = "",

    @SerializedName("profileImageUrl")
    val profileImageUrl: String = "",

    @SerializedName("data")
    val data: Any?
)
