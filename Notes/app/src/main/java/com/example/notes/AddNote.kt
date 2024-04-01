package com.example.notes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.Models.Note
import com.example.notes.databinding.ActivityAddNoteBinding
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

@Suppress("DEPRECATION")
class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private lateinit var  note : Note
    private lateinit var  old_note : Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.Title.setText(old_note.title)
            binding.Note.setText(old_note.note)
            isUpdate = true

        }
        catch (e : Exception){
            e.printStackTrace()
        }

        binding.ConfirmButton.setOnClickListener{
            val title = binding.Title.text.toString()
            val note_desc = binding.Note.text.toString()

            if(title.isNotEmpty() || note_desc.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")
                if(isUpdate){
                    note = Note(
                        old_note.id, title, note_desc, formatter.format(Date())
                    )
                }
                else{
                    note = Note(
                        null, title, note_desc, formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            else{
                Toast.makeText(this@AddNote, "Please enter some data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.BackButton.setOnClickListener{
            onBackPressed()
        }
    }
}