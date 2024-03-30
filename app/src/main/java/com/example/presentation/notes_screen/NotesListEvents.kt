package com.example.presentation.notes_screen

sealed class NotesListEvents {
    data class NoteCardClickedEvent(val id: Int): NotesListEvents()
    data object AddNoteButtonEvent: NotesListEvents()
}