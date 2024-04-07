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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.r_notes.R
import kotlinx.coroutines.flow.collectLatest



@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NoteScreenViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value
    val snackBarHostState = remember { SnackbarHostState() }

//    key1 = true parameter indicates the key used to determine when the effect should be relaunched.
//    true ensures that the LaunchedEffect is launched once and not relaunched again, as its key is a constant value (true).
//    This is often used when you want to execute an effect only once when the Composable is first composed,
//    and you don't want it to be triggered again during recompositions caused by other changes.
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is NoteScreenViewModel.UiEvent.ShowSnackbar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
                }
                is NoteScreenViewModel.UiEvent.SaveNote -> {
//                    snackBarHostState.showSnackbar(
//                        message = "Note Saved Sucessfully",
//                        duration = SnackbarDuration.Short
//                    )
                    navController.navigateUp()

                }
            }
        }
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(NoteScreenEvents.SaveNoteEvent)
//                    navController.navigateUp()
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
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
                value = titleState.text,
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
                value = contentState.text,
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