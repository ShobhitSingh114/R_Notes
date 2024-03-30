package com.example.presentation.add_edit_note_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Note
import com.example.domain.use_case.AddNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    private val addNote: AddNote,
//    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _noteTitle = mutableStateOf("")
    val noteTitle: State<String> = _noteTitle

    private val _noteContent = mutableStateOf("")
    val noteContent: State<String> = _noteContent

    fun onEvent(event: NoteScreenEvents) {
        when(event) {
            is NoteScreenEvents.TitleChangeEvent -> {
                _noteTitle.value = event.title
            }
            is NoteScreenEvents.ContentChangeEvent -> {
                _noteContent.value = event.content
            }
            NoteScreenEvents.SaveNoteEvent -> {
                viewModelScope.launch {
                    addNote(
                        Note(
                            title = noteTitle.value,
                            content = noteContent.value
                        )
                    )
                }
            }
        }
    }

}