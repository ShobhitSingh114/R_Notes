package com.example.presentation.add_edit_note_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.InvalidNoteException
import com.example.domain.model.Note
import com.example.domain.use_case.AddNote
import com.example.domain.use_case.GetNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    private val addNote: AddNote,
    private val getNote: GetNote,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _noteTitle = mutableStateOf(NoteTextFieldState())
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState())
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentNoteId: Int? = null


    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            // -1 = shows we don't have a note
            // or start of a new note

            // if (noteId has some value)
            if (noteId != -1){
                viewModelScope.launch {
                    // also = if not equal to null then proceed
                    getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title
                        )
                        _noteContent.value = _noteContent.value.copy(
                            text = note.content
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: NoteScreenEvents) {
        when(event) {
            is NoteScreenEvents.TitleChangeEvent -> {
                _noteTitle.value = _noteTitle.value.copy(
                    text = event.title
                )
            }
            is NoteScreenEvents.ContentChangeEvent -> {
                _noteContent.value = _noteTitle.value.copy(
                    text = event.content
                )
            }
            is NoteScreenEvents.SaveNoteEvent -> {
                viewModelScope.launch {
                    try {
                        addNote(
                            Note(
                                title = noteTitle.value.text,
                                content = noteContent.value.text,
                                id = currentNoteId,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }

                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        data object SaveNote: UiEvent()
    }

}