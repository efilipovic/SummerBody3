package enafilipovic.ferit.summerbody.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import enafilipovic.ferit.summerbody.R

class WeightEntryHolder( itemView: View): RecyclerView.ViewHolder(itemView) {
    var weightView: TextView? =  itemView.findViewById(R.id.weight_view)
    var timeView: TextView? =  itemView.findViewById(R.id.time_view)
}