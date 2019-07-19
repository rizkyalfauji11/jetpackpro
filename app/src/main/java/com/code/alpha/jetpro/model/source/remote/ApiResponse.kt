package com.code.alpha.jetpro.model.source.remote

import androidx.annotation.NonNull
import androidx.annotation.Nullable

class ApiResponse<T>(@NonNull val statusResponse: StatusResponse,
                     @Nullable val body: T,
                     @Nullable val message: String){
    companion object{
        fun <T> success(@Nullable body: T): ApiResponse<T> {
            return ApiResponse(StatusResponse.SUCCESS, body, "")
        }

        fun <T> empty(@Nullable body: T, msg: String): ApiResponse<T>{
            return ApiResponse(StatusResponse.EMPTY, body, msg)
        }

        fun <T> error(@Nullable body: T, msg: String): ApiResponse<T>{
            return ApiResponse(StatusResponse.ERROR, body, msg)
        }
    }
}