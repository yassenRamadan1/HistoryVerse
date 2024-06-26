package com.phdteam.historyverse.data.network

import com.phdteam.historyverse.data.network.response.BaseResponse
import retrofit2.Response

abstract class BaseRepository() {

    private suspend fun <T : Any> wrap(function: suspend () -> Response<BaseResponse<T>>): T {
        val response = function()
        return if (response.isSuccessful) {
            when (response.body()?.code) {
                "100" -> response.body()?.payload
                else -> throw Throwable(response.body()?.message.toString())
            } as T
        } else {
            throw Throwable("Network Error")
        }
    }


}