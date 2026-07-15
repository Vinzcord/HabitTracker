package com.vincent.projectuts_anmp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vincent.projectuts_anmp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}