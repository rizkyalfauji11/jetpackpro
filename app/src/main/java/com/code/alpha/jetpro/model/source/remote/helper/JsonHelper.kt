package com.code.alpha.jetpro.model.source.remote.helper

import android.app.Application
import android.util.Log
import com.code.alpha.jetpro.model.source.remote.response.MoviesModel
import com.google.gson.Gson

class JsonHelper(val application: Application) {

    private fun parsingFileToString(fileName: String): String {
        Log.e("Filename", fileName)
        return try {
            val inputStream = application.assets.open(fileName)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun loadMovies(fileName: String): MoviesModel {
        return Gson().fromJson(parsingFileToString(fileName), MoviesModel::class.java)
    }

}