package enafilipovic.ferit.summerbody

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import enafilipovic.ferit.summerbody.Models.Program
import kotlinx.android.synthetic.main.program.view.*

class ProgramHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(program: Program, programListener: ProgramListener){
        itemView.program_title.text = program.title
        itemView.program_calories.text= program.calories_burned
        itemView.program_duration.text = program.duration
        itemView.programImage.setBackgroundResource(program.image)
        itemView.setOnClickListener{programListener.onClick(program.id)}
    }
}



