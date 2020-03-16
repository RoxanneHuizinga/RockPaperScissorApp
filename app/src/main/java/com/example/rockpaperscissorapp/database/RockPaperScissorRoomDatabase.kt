package com.example.rockpaperscissorapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rockpaperscissorapp.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RockPaperScissorRoomDatabase : RoomDatabase() {

    abstract fun rockPaperScissorDao(): RockPaperScissorDao

    companion object {
        private const val DATABASE_NAME = "ROCK_PAPER_SCISSOR_DATABASE"

        @Volatile
        private var rockPaperScissorRoomDatabaseInstance : RockPaperScissorRoomDatabase? = null

        fun getDatabase(context: Context): RockPaperScissorRoomDatabase? {
            if (rockPaperScissorRoomDatabaseInstance == null) {
                synchronized(RockPaperScissorRoomDatabase::class.java) {
                    if (rockPaperScissorRoomDatabaseInstance == null) {
                        rockPaperScissorRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext,
                                RockPaperScissorRoomDatabase::class.java,
                                DATABASE_NAME
                            ).build()
                    }
                }
            }
            return rockPaperScissorRoomDatabaseInstance
        }
    }

}