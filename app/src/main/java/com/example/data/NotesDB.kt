package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotesEntity::class], version = 1)
abstract class NotesDB : RoomDatabase(){
    abstract val notesDao: NotesDao
}