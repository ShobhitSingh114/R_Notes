package com.example.domain.use_case

import com.example.domain.model.InvalidNoteException
import com.example.domain.model.Note
import com.example.domain.repo.NoteRepo
import javax.inject.Inject
import kotlin.jvm.Throws


class AddNote @Inject constructor(
    private val repo: NoteRepo
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty")
        }
        if (note.title.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty")
        }
        repo.insert(note)
    }
}