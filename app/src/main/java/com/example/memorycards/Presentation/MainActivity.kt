package com.example.memorycards.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memorycards.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment()
    }
    private fun openFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }
}