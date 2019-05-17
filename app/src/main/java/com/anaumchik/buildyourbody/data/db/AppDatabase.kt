package com.anaumchik.buildyourbody.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anaumchik.buildyourbody.data.entity.Player

@Database(entities = [Player::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object {
        private const val DATABASE_NAME = "Build-your-body.db"
        private var INSTANCE: AppDatabase? = null

        fun getDb(context: Context): AppDatabase {
            if (INSTANCE == null) synchronized(AppDatabase::class) {
                INSTANCE =
                    Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
            }

            return INSTANCE!!
        }
    }
}
