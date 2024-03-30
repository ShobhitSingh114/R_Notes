package com.example.presentation.util

sealed class Screen(val route: String) {
    data object NoteScreen: Screen("note_screen")
    data object NotesListScreen: Screen("notes_list_screen")
}