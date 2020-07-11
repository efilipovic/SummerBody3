package enafilipovic.ferit.summerbody.Adapters

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import enafilipovic.ferit.summerbody.Models.Program
import enafilipovic.ferit.summerbody.Models.WeightEntry
import enafilipovic.ferit.summerbody.ProgramHolder
import enafilipovic.ferit.summerbody.ProgramListener
import enafilipovic.ferit.summerbody.R
import enafilipovic.ferit.summerbody.ViewHolders.WeightEntryHolder
import java.text.SimpleDateFormat
import java.util.*

class WeightEntryAdapter (options: FirestoreRecyclerOptions<WeightEntry>): FirestoreRecyclerAdapter<WeightEntry,WeightEntryHolder>(options){
    override fun onBindViewHolder(weightViewHolder: WeightEntryHolder, position: Int, weightentry: WeightEntry) {
        // Show weight
        weightViewHolder.weightView?.text = "${weightentry.weight} kg"
        // Show date and time
        weightViewHolder.timeView?.text = convertLongToTime(weightentry.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeightEntryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weight_entry, parent, false)
        return WeightEntryHolder(view)
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd")
        return format.format(date)
    }

}

