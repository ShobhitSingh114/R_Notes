package com.example.presentation.notes_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Note
import com.example.domain.use_case.AddNote
import com.example.domain.use_case.DeleteNote
import com.example.domain.use_case.GetNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val addNote: AddNote,
    private val getNotes: GetNotes,
    private val deleteNote: DeleteNote
): ViewModel(){

    private val _state = mutableStateOf(NotesListState())
    val state: State<NotesListState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotesList()
    }
    fun onEvent(event: NotesListEvents) {
        when(event) {
            is NotesListEvents.DeleteNoteEvent -> {
                viewModelScope.launch {
                    deleteNote(event.deleteNote)
                    recentlyDeletedNote = event.deleteNote
                }

            }

//            If recentlyDeletedNote is not null, the function addNote() is called with recentlyDeletedNote
//            as an argument.
//            If recentlyDeletedNote is null, the function returns immediately from the enclosing
//            coroutine launched with launch. This means the subsequent code in the coroutine will not be
//            executed if recentlyDeletedNote is null.
            NotesListEvents.RestoreNote -> {
                viewModelScope.launch {
                    addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
        }
    }

    private fun getNotesList() {
        getNotesJob?.cancel()
        getNotesJob = getNotes().onEach { notes ->
            _state.value = _state.value.copy(
                notes = notes
            )
        }.launchIn(viewModelScope)

//        getNotes().onEach { notes ->
//            _state.value = _state.value.copy(
//                notes = notes
//            )
//        }.launchIn(viewModelScope)
    }

}