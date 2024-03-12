package com.example.domain.use_case

import com.example.domain.model.Note
import com.example.domain.repo.NoteRepo

class GetNote(
    private val repo: NoteRepo
) {
    suspend operator fun invoke(id: Int): Note? {
        return repo.getNote(id)
    }
}