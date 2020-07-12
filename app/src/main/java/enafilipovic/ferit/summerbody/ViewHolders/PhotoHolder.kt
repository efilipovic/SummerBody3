package enafilipovic.ferit.summerbody.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import enafilipovic.ferit.summerbody.R
import kotlinx.android.synthetic.main.photo_entry.view.*

class PhotoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var photoView :ImageView?=itemView.findViewById(R.id.photo)
}