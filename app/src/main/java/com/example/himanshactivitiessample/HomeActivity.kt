package com.example.himanshactivitiessample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import com.example.himanshactivitiessample.databinding.ActivityHomeBinding
import com.example.himanshactivitiessample.databinding.ActivityMainBinding
import com.example.himanshactivitiessample.utils.Constant
import com.example.himanshactivitiessample.utils.PrefHelper

class HomeActivity : AppCompatActivity() {

    lateinit var prefHelper: PrefHelper
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
          var message:String = intent.getStringExtra("welcomeMessage").toString()



     //   prefHelper = PrefHelper(this)
     //   var message:String?= prefHelper.getString( Constant.PREF_USERNAME )
     //   var imagePath:String?= prefHelper.getString( Constant.PREF_IMAGE_PATH )


        binding.welcomeMessage.text=message

      //  binding.setDisplayImage.setImageURI(imagePath?.toUri())
//        binding.signOut.setOnClickListener {
//            prefHelper.clear()
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }


    }
}