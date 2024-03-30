package com.example.presentation.notes_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.notes_screen.components.NoteCard

@Composable
fun NotesListScreen(
    viewModel: NotesListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = { Text(text = "R_Notes \uD83D\uDCDD") },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.notes) {
                    NoteCard(it)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}