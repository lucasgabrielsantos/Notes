package br.com.codelab.mvvmproject.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import br.com.codelab.mvvmproject.data.NoteDao
import br.com.codelab.mvvmproject.data.NoteDatabase
import br.com.codelab.mvvmproject.model.Note


class NoteRepository(context: Context) {
    private val noteDao: NoteDao
    private val allNotes: LiveData<List<Note>>

    init {
        val database: NoteDatabase = NoteDatabase.getInstance(context)
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        insertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        updateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        deleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        deleteAllNoteAsyncTask(noteDao).execute()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    companion object {
        private class insertNoteAsyncTask() : AsyncTask<Note, Void, Void>() {
            lateinit var noteDao: NoteDao

            constructor(noteDao: NoteDao) : this() {
                this.noteDao = noteDao
            }


            override fun doInBackground(vararg notes: Note): Void? {
                noteDao.insert(notes[0])
                return null
            }
        }

        private class updateNoteAsyncTask() : AsyncTask<Note, Void, Void>() {
            lateinit var noteDao: NoteDao

            constructor(noteDao: NoteDao) : this() {
                this.noteDao = noteDao
            }


            override fun doInBackground(vararg notes: Note): Void? {
                noteDao.insert(notes[0])
                return null
            }

        }

        private class deleteNoteAsyncTask() : AsyncTask<Note, Void, Void>() {
            lateinit var noteDao: NoteDao

            constructor(noteDao: NoteDao) : this() {
                this.noteDao = noteDao
            }


            override fun doInBackground(vararg notes: Note): Void? {
                noteDao.delete(notes[0])
                return null
            }

        }

        private class deleteAllNoteAsyncTask() : AsyncTask<Void, Void, Void>() {
            lateinit var noteDao: NoteDao

            constructor(noteDao: NoteDao) : this() {
                this.noteDao = noteDao
            }

            override fun doInBackground(vararg void: Void): Void? {
                noteDao.deleteAllNotes()
                return null
            }
        }
    }
}