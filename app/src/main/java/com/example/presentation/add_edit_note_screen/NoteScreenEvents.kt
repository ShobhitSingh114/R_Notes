package com.example.presentation.add_edit_note_screen

sealed class NoteScreenEvents {
    data class TitleChangeEvent(val title: String): NoteScreenEvents()
    data class ContentChangeEvent(val content: String): NoteScreenEvents()
    data object SaveNoteEvent: NoteScreenEvents()
//    data object AddUpdateNote: NoteScreenEvents()
}