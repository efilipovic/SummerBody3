package enafilipovic.ferit.summerbody.Adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import enafilipovic.ferit.summerbody.Models.PhotoEntry
import enafilipovic.ferit.summerbody.R
import enafilipovic.ferit.summerbody.ViewHolders.PhotoHolder
import java.io.File


class PhotoAdapter(photos:ArrayList<PhotoEntry>): RecyclerView.Adapter<PhotoHolder>() {

    private val photos: ArrayList<PhotoEntry>

    init {
        this.photos=photos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        //Inflate photo xml
        val photoView = LayoutInflater.from(parent.context).inflate(R.layout.photo_entry, parent, false)
        return PhotoHolder(photoView)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.photoView!!.scaleType=ImageView.ScaleType.CENTER_CROP
        setImageFromPath(photos.get(position).path, holder.photoView!!)

    }

    override fun getItemCount(): Int {
       return photos.size
    }

    private fun setImageFromPath(path:String,image:ImageView){
        //Get image file
        val imgFile =  File(path)
        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            //Set img to view
            image.setImageBitmap(myBitmap)
        }
    }
}