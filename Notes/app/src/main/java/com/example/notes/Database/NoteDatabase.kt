package com.example.notes.Database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.Models.Note
import com.example.notes.utils.DATABASE_NAME

@Database(entities = arrayOf(Note::class), version = 1)

abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDao() : NoteDAO
    companion object{
        @Volatile

        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                Log.d("NVM", "Start")
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java, DATABASE_NAME
                ).build()
                Log.d("NVM", "Done")//The problem is it doesnt build and it wont fix
                INSTANCE = instance
                instance
            }
        }

    }


}