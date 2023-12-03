package com.example.notesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import com.example.notesapp.databinding.ActivityNoteAddBinding
import java.text.SimpleDateFormat
import java.util.Date
import com.example.notesapp.Models.Note

class NoteAdd : AppCompatActivity() {

    private lateinit var binding : ActivityNoteAddBinding

    private lateinit var note : Note

    private lateinit var old_note  : Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            old_note= intent.getSerializableExtra("current_note")as Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate= true



        }catch ( e: Exception){
            e.printStackTrace()

        }

        binding.imgCheck.setOnClickListener{
            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.text.toString()

            if (title.isNotEmpty()){     // || note.isNotEmpty()){

                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z")

                if (isUpdate){
                    note= Note(
                        old_note.id,title,note_desc,formatter.format(Date())
                    )
                }else{
                    note = Note(
                        null,title,note_desc,formatter.format(Date())
                    )
                }

                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK,intent)
                finish()

            }else{
                Toast.makeText(this@NoteAdd,"PLease enter some data ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.imgBackArrow.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}