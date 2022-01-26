package com.example.jurnal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jurnal.fragments.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.conteiner_frag, MainFragment())
            .commit()
    }
}