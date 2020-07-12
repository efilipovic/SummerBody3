package enafilipovic.ferit.summerbody.Activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.util.Log.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import enafilipovic.ferit.summerbody.Adapters.PhotoAdapter
import enafilipovic.ferit.summerbody.Models.PhotoEntry
import enafilipovic.ferit.summerbody.R
import kotlinx.android.synthetic.main.activity_photo_tracker.*
import kotlinx.android.synthetic.main.nav_header.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.ArrayList

class PhotoTrackerActivity : AppCompatActivity() {
    lateinit var photoPath : String
    val REQUEST_TAKE_PHOTO=1
    lateinit var filepaths : List<PhotoEntry>


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_tracker)

        btn_add_photo.setOnClickListener{
            takePicture()
        }
        val permission = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),2000)
        }else{
            //show images
            showImages()
        }

        }
    //show images on the screen
    private fun showImages(){
        var storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath

        filepaths = listAllFiles(storageDir)
        val photoView = findViewById<RecyclerView>(R.id.photo_view)
        photoView.layoutManager = LinearLayoutManager(this)

        var images = listAllImages();
        photoView.adapter=PhotoAdapter(images)
    }

   @SuppressLint("MissingSuperCall")
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       //   Update recycler view with new img
        if(requestCode==REQUEST_TAKE_PHOTO&&resultCode==Activity.RESULT_OK){
            showImages()
        }
    }

    //load files from dir
    private fun listAllFiles(paths:String):List<PhotoEntry>{
        var allFiles=ArrayList<PhotoEntry>()
        val file =  File(paths)
        var files=file.listFiles()
        if(files!=null){
            for(file  in files){
                val photo = PhotoEntry(file.absolutePath)
                allFiles.add(photo)
            }
        }
        return allFiles
    }

    //load images from list
    private fun listAllImages():ArrayList<PhotoEntry>{
        var allImages=ArrayList<PhotoEntry>()

            for(file  in filepaths){
                allImages.add(file)
            }

        return allImages
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==1000){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                showImages()
            }else{
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        if(intent.resolveActivity(packageManager)!=null){
            var photoFile: File?=null            
            photoFile=createImageFile()
            if(photoFile!=null){
                val photoUri=FileProvider.getUriForFile(this,"enafilipovic.ferit.summerbody.fileprovider",photoFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                startActivityForResult(intent,REQUEST_TAKE_PHOTO)
            }
        }
    }


    private fun createImageFile(): File? {
        var fileName = Date().time.toString()
        var storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(fileName,".jpg",storageDir)
        photoPath =image.absolutePath
        return image
    }

}
