package com.pratyushvkp.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pratyushvkp.notes.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}