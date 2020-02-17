package br.com.codelab.mvvmproject.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.codelab.mvvmproject.R

class AddNoteActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var numberPicker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        initVariables()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initVariables() {
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPicker = findViewById(R.id.number_picker_priority)

        numberPicker.apply {
            minValue = 1
            maxValue = 10
        }
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Adicionar Notas"
    }

    private fun saveNote() {
        val title = editTextTitle.text
        val description = editTextDescription.text
        val priority = numberPicker.value

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(
                this@AddNoteActivity, "Por favor, insira um texto e descrição",
                Toast.LENGTH_LONG).show()
        } else {
            val data = Intent().apply {
                putExtra(EXTRA_TITLE, title.toString())
                putExtra(EXTRA_DESCRIPTION, description.toString())
                putExtra(EXTRA_PRIORITY, priority)
            }
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    companion object {
        const val EXTRA_DESCRIPTION = "br.com.codelab.mvvmproject.model.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "br.com.codelab.mvvmproject.model.EXTRA_PIORITY"
        const val EXTRA_TITLE = "br.com.codelab.mvvmproject.model.EXTRA_TITLE"
    }
}
