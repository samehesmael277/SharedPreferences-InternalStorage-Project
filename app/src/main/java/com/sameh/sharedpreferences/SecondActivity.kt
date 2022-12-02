package com.sameh.sharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sameh.sharedpreferences.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val FILE_NAME = "myFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateFile.setOnClickListener {
            writeOnInternalStorageFile()
        }

        binding.btnReadFromFile.setOnClickListener {
            readFromInternalStorageFile()
        }

    }

    private fun writeOnInternalStorageFile() {
        try {
            val fileContents = "I written from Internal Storage app by Sameh Esmael"
            this.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
                it.write(fileContents.toByteArray())
            }
            Toast.makeText(this, "saved success", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFromInternalStorageFile() {
        try {
            val fileStream = openFileInput(FILE_NAME)
            val bufferedReader = fileStream.bufferedReader()
            val stringBuilder = StringBuilder()
            bufferedReader.forEachLine {
                stringBuilder.append(it)
            }
            binding.tvDataReader.text = stringBuilder
        }
        catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}