package com.example.domain.use_case

import com.example.domain.model.Note
import com.example.domain.repo.NoteRepo
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val repo: NoteRepo
) {
    operator fun invoke(): Flow<List<Note>> {
        return repo.getNotes()
    }
}