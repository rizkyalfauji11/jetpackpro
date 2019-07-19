package com.code.alpha.jetpro.model.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.code.alpha.jetpro.model.source.local.entity.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class JetProDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    companion object{
        private var INSTANCE: JetProDatabase? = null

        fun getInstance(context: Context): JetProDatabase{
            if(INSTANCE == null){
                synchronized(JetProDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        JetProDatabase::class.java, "db_jetpro.db")
                        .build()
                }
            }

            return INSTANCE as JetProDatabase
        }
    }
}