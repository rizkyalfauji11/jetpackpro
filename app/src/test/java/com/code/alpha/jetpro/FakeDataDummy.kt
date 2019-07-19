package com.code.alpha.jetpro

import com.code.alpha.jetpro.model.Constant
import com.code.alpha.jetpro.model.MoviesData
import java.io.IOException

class FakeDataDummy {
    companion object{
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


}