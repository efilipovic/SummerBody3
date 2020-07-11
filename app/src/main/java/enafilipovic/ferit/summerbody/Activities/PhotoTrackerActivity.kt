package enafilipovic.ferit.summerbody.Activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import enafilipovic.ferit.summerbody.R
import kotlinx.android.synthetic.main.activity_photo_tracker.*
import kotlinx.android.synthetic.main.nav_header.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PhotoTrackerActivity : AppCompatActivity() {
    lateinit var photoPath : String
    val REQUEST_TAKE_PHOTO=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_tracker)

        btn_add_photo.setOnClickListener{
            takePicture()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==REQUEST_TAKE_PHOTO&&resultCode==Activity.RESULT_OK){
            progress_picture.rotation=90f
            progress_picture.setImageURI(Uri.parse(photoPath))
        }
    }

    private fun createImageFile(): File? {
        var fileName = "MyPicture"
        var storageDir=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(fileName,".jpg",storageDir)

        photoPath =image.absolutePath
        return image
    }

}
