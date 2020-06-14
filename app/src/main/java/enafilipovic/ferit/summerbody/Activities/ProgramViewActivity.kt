package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import enafilipovic.ferit.summerbody.*
import enafilipovic.ferit.summerbody.Repositories.ProgramRepo
import kotlinx.android.synthetic.main.activity_programs.*


class ProgramViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programs)
        updateUI()
    }

    private fun updateUI(){
        programView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        displayData()
    }
    private fun displayData() {
        val programListener = object:
            ProgramListener {
            override fun onClick(id: Int) {
                val Detailsintent : Intent = Intent(this@ProgramViewActivity,
                    ProgramDetailsActivity::class.java)
                Detailsintent.putExtra(ProgramDetailsActivity.KEY_ID,id)
                startActivity(Detailsintent)
            }
        }
        programView.adapter = ProgramAdapter(
            ProgramRepo.programs,
            programListener
        )

    }
}


