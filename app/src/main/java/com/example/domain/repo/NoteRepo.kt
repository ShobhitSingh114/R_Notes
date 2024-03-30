package com.example.domain.repo

import com.example.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepo {
    suspend fun insert(note: Note)
//    suspend fun update(note: Note)
    suspend fun delete(note: Note)
    fun getNotes(): Flow<List<Note>>
    suspend fun getNote(id: Int): Note?

}