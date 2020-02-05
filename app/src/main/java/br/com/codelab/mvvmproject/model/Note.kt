package br.com.codelab.mvvmproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val title: String,

    val description: String,

    val priority: Int

)