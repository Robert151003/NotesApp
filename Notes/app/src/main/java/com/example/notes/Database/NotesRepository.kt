package com.example.notes.Database

import com.example.notes.Models.Note
import androidx.lifecycle.LiveData


class NotesRepository(private val noteDao: NoteDAO) {

    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun  insert(note:Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note.id, note.title, note.note)
    }

}