package com.sameh.sharedpreferences

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sameh.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val userName = "UserName"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            writeOnSharedPreferencesFile()

            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        binding.btnLoad.setOnClickListener {
            readFromSharedPreferencesFile()
        }

    }

    private fun writeOnSharedPreferencesFile() {
        try {
            val sharedPreferences = getSharedPreferences("userName", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(userName, binding.edUsername.text.toString())
            editor.apply()
            Toast.makeText(this, "User name saved", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFromSharedPreferencesFile() {
        try {
            val sharedPreferences = getSharedPreferences("userName", Context.MODE_PRIVATE)
            val userNameFromSharedPreferences =  sharedPreferences.getString(userName, "no username yet")
            binding.edUsername.setText(userNameFromSharedPreferences)
        }
        catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}