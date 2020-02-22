package br.com.codelab.mvvmproject.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import br.com.codelab.mvvmproject.data.NoteDao
import br.com.codelab.mvvmproject.data.NoteDatabase
import br.com.codelab.mvvmproject.model.Note


class NoteRepository(context: Context?) {
    private val noteDao: NoteDao
    private val allNotes: LiveData<List<Note>>

    fun insert(note: Note?) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note?) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note?) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(noteDao).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Note, Void, Void?>() {
        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.insert(notes[0])
            return null
        }
    }

    private class UpdateNoteAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Note, Void, Void?>() {
        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.update(notes[0])
            return null
        }

    }

    private class DeleteNoteAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Note, Void, Void?>() {
        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.delete(notes[0])
            return null
        }

    }

    private class DeleteAllNotesAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Void, Void, Void?>() {
        override fun doInBackground(vararg voids: Void): Void? {
            noteDao.deleteAllNotes()
            return null
        }

    }

    init {
        val database = NoteDatabase.getInstance(context!!)
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }
}


