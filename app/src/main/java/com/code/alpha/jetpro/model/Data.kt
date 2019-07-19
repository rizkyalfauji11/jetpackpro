package com.code.alpha.jetpro.model

import java.io.IOException

object Data{
    fun getJsonToString(rawId: String?): String {
        try {
            return if (rawId.equals(Constant.movie)) {
                MoviesData.movieJson
            } else {
                MoviesData.tv_show
            }

        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return ""
    }


}