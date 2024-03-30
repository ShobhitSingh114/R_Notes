package com.example.presentation.add_edit_note_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.r_notes.R

@Composable
fun NoteScreen(
    viewModel: NoteScreenViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(NoteScreenEvents.SaveNoteEvent)
                },
                shape = CircleShape
            ) {
                Icon(painter = painterResource(id = R.drawable.save), contentDescription = "Save Icon")
            }
        },
        snackbarHost ={ SnackbarHost(hostState = snackBarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(paddingValues)
        ) {
            // Title
            TextField(
                value = titleState,
                onValueChange = {
                    viewModel.onEvent(NoteScreenEvents.TitleChangeEvent(it))
                },
                placeholder = { Text(text = "Enter Text...") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = contentState,
                onValueChange = {
                    viewModel.onEvent(NoteScreenEvents.ContentChangeEvent(it))
                },
                placeholder = { Text(text = "Enter Content...") },
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}