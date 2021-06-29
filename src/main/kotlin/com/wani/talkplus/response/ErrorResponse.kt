package com.wani.talkplus.response

data class ErrorResponse(
    val requestId: String,
    val error: Boolean,
    val code: String,
    val message: String
)
