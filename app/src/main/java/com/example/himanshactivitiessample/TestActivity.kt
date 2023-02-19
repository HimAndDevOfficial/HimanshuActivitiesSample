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

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefHelper = PrefHelper(this)


        binding.login.setOnClickListener {

            if (binding.inputUsername.text!!.isNotEmpty() && binding.inputPassword.text!!.isNotEmpty()) {
                saveSession( binding.inputUsername.text.toString(),  binding.inputPassword.text.toString() )
                moveToWelcomeScreen()
            }
            else
                showMessage("Enter valid credentials")
        }

        binding.createNewAccount.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.chooseImage.setOnClickListener {
            ImagePicker.with(this)   // Internally we are using new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .saveDir(File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!, "ImagePicker"))
                .start()

        }
    }
    override fun onStart() {
        super.onStart()
        if (prefHelper.getBoolean( Constant.PREF_IS_LOGIN )) {
            moveToWelcomeScreen()
        }
    }
    private fun saveSession(username: String, password: String){
        prefHelper.put( Constant.PREF_USERNAME, username )
        prefHelper.put( Constant.PREF_PASSWORD, password )
        prefHelper.put( Constant.PREF_IS_LOGIN, true)
    }

    private fun moveToWelcomeScreen(){
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
    private fun showMessage(message: String) {
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!

            // Use Uri object instead of File to avoid storage permissions (Avoid Below code)
//            File f = new File(Environment.getExternalStorageDirectory().toString());
//            for (File temp : f.listFiles()) {
//                if (temp.getName().equals("temp.jpg")) {
//                    f = temp;
//                    break;

            Log.d("imagepath",uri.path.toString())
            binding.setImage.setImageURI(uri)
            prefHelper.put(Constant.PREF_IMAGE_PATH,uri.path.toString())

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}