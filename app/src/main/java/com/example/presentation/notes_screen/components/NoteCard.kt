package com.example.presentation.notes_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Note
import com.example.presentation.notes_screen.NotesListViewModel

@Composable
fun NoteCard(
    note: Note
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
//            .padding(15.dp)
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary)
    ) {
        Column(
            modifier = Modifier
//                .fillMaxSize()
                .padding(10.dp)
        ) {
            Text(
                text = note.title,
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                maxLines = 3,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NoteCardPreview() {
    val state = Note(
        title = "Title",
        content = "Content Line 1 \n " +
                "Content Line 2 \n " +
                "Content Line 3 \n " +
                "Content Line 4"
    )
    NoteCard(note = state)
}