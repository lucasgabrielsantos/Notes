package br.com.codelab.mvvmproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.codelab.mvvmproject.R
import br.com.codelab.mvvmproject.viewmodel.NoteViewModel


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter = NoteAdapter()
        recyclerView.adapter = adapter


        val noteViewModel =
            ViewModelProviders.of(this, NoteViewModel.ViewModelFactory(this@MainActivity))
                .get(NoteViewModel::class.java)
        noteViewModel.getAllNotes().observe(this, Observer { notes ->
            adapter.setNotes(notes)
        })
    }
}
