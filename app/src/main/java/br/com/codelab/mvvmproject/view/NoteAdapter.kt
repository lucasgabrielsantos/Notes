package br.com.codelab.mvvmproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.codelab.mvvmproject.R
import br.com.codelab.mvvmproject.model.Note
import java.util.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var notes: List<Note> = ArrayList()

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView by lazy {
            itemView.findViewById<TextView>(R.id.text_view_title)
        }
        val textViewDescription: TextView by lazy {
            itemView.findViewById<TextView>(R.id.text_view_description)
        }
        val textViewPriority: TextView by lazy {
            itemView.findViewById<TextView>(R.id.text_view_priority)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)

        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote: Note = notes[position]

        holder.apply {
            textViewTitle.text = currentNote.title
            textViewDescription.text = currentNote.description
            textViewPriority.text = currentNote.priority.toString()
        }
    }

    fun setNotes(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

}