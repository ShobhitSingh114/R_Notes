package com.example.data.repo

import com.example.data.data_source.NoteDao
import com.example.domain.model.Note
import com.example.domain.repo.NoteRepo
import kotlinx.coroutines.flow.Flow

class NoteRepoImpl(
    private val dao: NoteDao
): NoteRepo {
    override suspend fun insert(note: Note) {
        return dao.insert(note)
    }

//    override suspend fun update(note: Note) {
//        return dao.update(note)
//    }

    override suspend fun delete(note: Note) {
        return dao.delete(note)
    }

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNote(id: Int): Note? {
        return dao.getNote(id)
    }
}