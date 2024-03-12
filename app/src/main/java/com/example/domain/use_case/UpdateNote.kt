package com.example.domain.use_case

import com.example.domain.model.Note
import com.example.domain.repo.NoteRepo

class UpdateNote(
    private val repo: NoteRepo
) {
    suspend operator fun invoke(note: Note) {
        repo.update(note)
    }
}