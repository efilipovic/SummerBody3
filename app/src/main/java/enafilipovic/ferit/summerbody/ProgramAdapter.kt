package enafilipovic.ferit.summerbody

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import enafilipovic.ferit.summerbody.Models.Program

class ProgramAdapter(
    programs: MutableList<Program>,
    programListener: ProgramListener
):RecyclerView.Adapter<ProgramHolder>(){

    private val programs: MutableList<Program>
    private val programListener: ProgramListener

    init {
        this.programs = mutableListOf()
        this.programs.addAll(programs)
        this.programListener=programListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramHolder {
        val programView = LayoutInflater.from(parent.context).inflate(R.layout.program, parent, false)
        return ProgramHolder(programView)
    }
    override fun getItemCount(): Int {
       return programs.size
    }

    override fun onBindViewHolder(holder: ProgramHolder, i: Int) {
        holder.bind(programs[i],programListener)
    }

}


