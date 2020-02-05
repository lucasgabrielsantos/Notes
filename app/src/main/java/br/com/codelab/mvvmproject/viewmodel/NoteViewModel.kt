package br.com.codelab.mvvmproject.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.codelab.mvvmproject.model.Note
import br.com.codelab.mvvmproject.repository.NoteRepository

class NoteViewModel(context: Context) : ViewModel() {

    private var repository: NoteRepository = NoteRepository(context)
    private var allNotes: LiveData<List<Note>>


    init {
        allNotes = repository.getAllNotes()
    }

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun update(note: Note) {
        repository.update(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }
    ;
    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?>
                create(modelClass: Class<T>):
                T = NoteViewModel(context) as T

    }
}