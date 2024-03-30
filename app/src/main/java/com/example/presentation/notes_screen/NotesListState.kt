package com.example.presentation.notes_screen

import com.example.domain.model.Note

data class NotesListState(
    val notes: List<Note> = emptyList()
)
