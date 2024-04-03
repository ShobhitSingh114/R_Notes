package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
//    @PrimaryKey(autoGenerate = true)
    @PrimaryKey
    val id: Int? = null,
//    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long
)

class InvalidNoteException(message: String): Exception(message)