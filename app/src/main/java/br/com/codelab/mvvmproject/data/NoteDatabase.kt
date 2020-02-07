package br.com.codelab.mvvmproject.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.codelab.mvvmproject.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {


    abstract fun noteDao(): NoteDao

    companion object {
        private lateinit var instance: NoteDatabase

        fun getInstance(context: Context): NoteDatabase {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build()
            }
            return instance
        }

        private val roomCallBack: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }

        private class PopulateDbAsyncTask() : AsyncTask<Note, Void, Void>() {

            lateinit var noteDao: NoteDao

            constructor(db: NoteDatabase) : this() {
                noteDao = db.noteDao()
            }

            override fun doInBackground(vararg params: Note): Void? {
                noteDao.insert(Note( "title 1", "Description 1", 5))
                noteDao.insert(Note( "title 2", "Description 2", 6))
                noteDao.insert(Note( "title 3", "Description 3", 9))
                return null
            }
        }
    }

}