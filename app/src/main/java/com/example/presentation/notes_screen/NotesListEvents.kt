package com.example.presentation.notes_screen

import com.example.domain.model.Note

sealed class NotesListEvents {
    // these 2 both events are contains navigation so can be applied directly
//    so these 2 events are not needed
//    data class NoteCardClickedEvent(val id: Int): NotesListEvents()
//    data object AddNoteButtonEvent: NotesListEvents()
    data class DeleteNoteEvent(val deleteNote: Note): NotesListEvents()
    data object RestoreNote: NotesListEvents()
}