package br.com.codelab.mvvmproject.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.codelab.mvvmproject.R
import br.com.codelab.mvvmproject.model.Note
import br.com.codelab.mvvmproject.view.AddNoteActivity.Companion.EXTRA_DESCRIPTION
import br.com.codelab.mvvmproject.view.AddNoteActivity.Companion.EXTRA_PRIORITY
import br.com.codelab.mvvmproject.view.AddNoteActivity.Companion.EXTRA_TITLE
import br.com.codelab.mvvmproject.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val adapter = NoteAdapter()

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProviders.of(this, NoteViewModel.ViewModelFactory(this@MainActivity)).get(NoteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickBtnAddNote()
        instanceRvInAdapter()


        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(adapter.NoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recycler_view)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val title = data!!.getStringExtra(EXTRA_TITLE)
            val description = data.getStringExtra(EXTRA_DESCRIPTION)
            val priority = data.getIntExtra(EXTRA_PRIORITY, ADD_NOTE_REQUEST)
            val note = Note(title, description, priority)


            noteViewModel.insert(note)
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val ADD_NOTE_REQUEST = 1
    }

    private fun clickBtnAddNote() {
        button_add_note.setOnClickListener {
            intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }
    }

    private fun instanceRvInAdapter() {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = adapter
        noteViewModel.getAllNotes().observe(this, Observer { notes ->
            adapter.setNotes(notes)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel.deleteAllNotes()
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
