package com.code.alpha.jetpro.vo

import androidx.annotation.NonNull
import androidx.annotation.Nullable


class Resource<T>(
    @NonNull val status: Status,
    @Nullable val data: T?,
    @Nullable val message: String
) {

    companion object {
        fun <T> success(@Nullable data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(@Nullable data: T, msg: String): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(@Nullable data: T?): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other == null || javaClass != other::javaClass) {
            return false
        }

        val resource = other as Resource<*>

        if (status != resource.status) {
            return false
        }

        if (message != resource.message) {
            return false
        }

        return data?.equals(resource.data) ?: (resource.data == null)
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}'
    }

}