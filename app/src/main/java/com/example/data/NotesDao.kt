package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Upsert
    suspend fun upsertNotes(notes: NotesEntity)
    @Delete
    suspend fun deleteNotes(notes: NotesEntity)
    @Query("SELECT * FROM NotesEntity ORDER BY id DESC")
    fun readAllNotes(): Flow<List<NotesEntity>>
    @Query("SELECT * FROM NotesEntity WHERE id = :id")
    fun readSpecificNote(id: Int): Flow<NotesEntity>
}