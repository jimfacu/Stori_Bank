package com.mobilenik.storibank.Views

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.storage.FirebaseStorage
import com.mobilenik.storibank.Common.Constants
import com.mobilenik.storibank.R
import com.mobilenik.storibank.Utils.StoriBankPreferences
import com.mobilenik.storibank.Utils.UtilsInterface
import com.mobilenik.storibank.Views.RegisterFragments.Step2RegisterFragment
import com.mobilenik.storibank.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.File


@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,UtilsInterface{

    private val MY_PERMISSIONS_REQUEST_CAMERA = 99

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK  ) {

                val imageUri = result.data!!.extras
                val uriImage = bitmapToUri(imageUri!!.get("data") as Bitmap)
                sendUriImageToFragment(uriImage)

                Log.d("Imagen",uriImage.toString())
            }
        }

    private fun sendUriImageToFragment(uriImage: Uri) {

        val navHostFragment: NavHostFragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        if( navHostFragment!!.childFragmentManager.fragments[0] is Step2RegisterFragment ){
            val fragment =  navHostFragment.childFragmentManager.fragments[0] as Step2RegisterFragment
            fragment.reciveImageUrl(uriImage)
        }


    }


    private fun bitmapToUri(bitmap: Bitmap): Uri {

       val file = File(this.cacheDir,"image")
       file.delete()
       file.createNewFile()
       val fileOutputStream = file.outputStream()
       val byteArrayOutputStream = ByteArrayOutputStream()
           bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream)
       val bytearray = byteArrayOutputStream.toByteArray()
       fileOutputStream.write(bytearray)
       fileOutputStream.flush()
       fileOutputStream.close()
       byteArrayOutputStream.close()

       return file.toUri()
    }



    fun checkCameraPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestCameraPermission()
        }else{
            takePictureLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            Constants.MY_PERMISSIONS_REQUEST_CAMERA
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        takePictureLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                    }

                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    fun showProgress(){
        binding.ivProgressBar.visibility = LottieAnimationView.VISIBLE
    }

    fun hideProgress(){
        binding.ivProgressBar.visibility = LottieAnimationView.GONE
    }

    override fun takePicture() {
        checkCameraPermission()
    }
}