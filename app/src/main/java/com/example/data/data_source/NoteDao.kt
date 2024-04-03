package com.example.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)
//    @Update
//    suspend fun update(note: Note)
    @Delete
    suspend fun delete(note: Note)

//    @Query("SELECT * FROM Note ORDER BY id DESC")
    @Query("SELECT * FROM Note ORDER BY timestamp DESC")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE id=:id")
//    fun getNote(id: Int): Flow<Note>
    suspend fun getNoteById(id: Int): Note?
}