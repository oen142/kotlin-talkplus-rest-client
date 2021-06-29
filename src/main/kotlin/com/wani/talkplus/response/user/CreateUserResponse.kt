package com.wani.talkplus.response.user

data class CreateUserResponse(
    val user: CreateUserDataResponse,
    val loginToken: String
)

data class CreateUserDataResponse(
    val id: String,
    val username: String,
    val profileImageUrl: String,
    val data: Any,
    val updatedAt: Long,
    val createdAt: Long
)

