package com.coala.play_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coala.play_app.db.dao.PlayDao
import com.coala.play_app.db.entity.Play

@Database(entities = arrayOf(Play::class),version = 1)
abstract class PlayDatabase : RoomDatabase(){
    abstract fun playDao(): PlayDao

    companion object {
        private var INSTANCE : PlayDatabase? = null

        fun getInstance(context:Context) : PlayDatabase? {
            if(INSTANCE == null){
                synchronized(PlayDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,PlayDatabase::class.java,"play.db")
                        .createFromAsset("database/play.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries().build();
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}