package com.example.di

import android.app.Application
import androidx.room.Room
import com.example.data.data_source.NoteDatabase
import com.example.data.repo.NoteRepoImpl
import com.example.domain.repo.NoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "notes_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepo(db: NoteDatabase): NoteRepo {
        return NoteRepoImpl(db.noteDao)
    }

}