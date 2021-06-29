package com.wani.talkplus.exception

import retrofit2.HttpException

class TalkPlusResponseException(
    error: String,
    exception: HttpException
) : Exception(error, exception)