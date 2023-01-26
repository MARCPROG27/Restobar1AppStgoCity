package com.example.restobar1appstgocity.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConsumosEntity::class], version = 2)

abstract class ConsumosDatabase : RoomDatabase() {
    abstract fun getConsumoDao(): ConsumosDao

    companion object {

        @Volatile

        private var INSTANCE: ConsumosDatabase? = null


        fun getDatabase1(context: Context): ConsumosDatabase {
            val tempInntance = INSTANCE

            if (tempInntance != null) {

                return tempInntance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ConsumosDatabase::class.java,
                    "mirestobar"
                )
                    .build()
                INSTANCE = instance
                return instance

            }
        }

    }


}