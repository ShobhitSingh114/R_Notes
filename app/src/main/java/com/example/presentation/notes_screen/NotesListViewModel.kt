package com.example.presentation.notes_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.domain.model.Note
import com.example.domain.use_case.GetNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val getNotes: GetNotes
): ViewModel(){

    private val _state = mutableStateOf(NotesListState())
    val state: State<NotesListState> = _state

    private var getNotesJob: Job? = null

    init {
        getNotesList()
    }
    fun onEvent(event: NotesListEvents) {
        when(event) {
            NotesListEvents.AddNoteButtonEvent -> {

            }
            is NotesListEvents.NoteCardClickedEvent -> {

            }
        }

    }

    private fun getNotesList() {
//        getNotesJob?.cancel()
//        getNotesJob = getNotes().onEach {
//        }
        getNotes().onEach {
            _state.value = _state.value.copy(
                notes = it
            )
        }
    }

}