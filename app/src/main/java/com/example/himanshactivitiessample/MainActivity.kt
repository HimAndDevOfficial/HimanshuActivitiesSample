package com.example.himanshactivitiessample

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.example.himanshactivitiessample.databinding.ActivityMainBinding
import com.example.himanshactivitiessample.utils.Constant
import com.example.himanshactivitiessample.utils.PrefHelper
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.login.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("welcomeMessage",binding.inputUsername.text.toString())
            startActivity(intent)
        }


    }
}