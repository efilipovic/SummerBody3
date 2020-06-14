package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import enafilipovic.ferit.summerbody.R
import enafilipovic.ferit.summerbody.Repositories.ProgramRepo
import kotlinx.android.synthetic.main.activity_program_details.*


class ProgramDetailsActivity : AppCompatActivity() {
    companion object {
        const val KEY_ID: String = "KEY_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_details)

        val program_id = intent.getIntExtra(KEY_ID,0)

        back_arrow.setOnClickListener{
            startActivity(Intent(this, ProgramViewActivity::class.java))
            finish()
        }

        button_startworkout.setOnClickListener{
            val Workoutintent = Intent(applicationContext, ProgramWorkoutActivity::class.java)
            // val Workoutintent = Intent(this,ProgramWorkoutActivity::class.java)
            Workoutintent.putExtra(ProgramWorkoutActivity.KEY_PROGRAM_ID, program_id)
            startActivity(Workoutintent)
            finish()
        }
        setUpUI(program_id)

    }
    private fun setUpUI(id:Int){
        //val program_id=intent.getIntExtra(KEY_ID,0)
        title_program_details.text = ProgramRepo.programs[id-1].title
        description_program.text= ProgramRepo.programs[id-1].description
        program_duration_details.text= ProgramRepo.programs[id-1].duration
        program_calories_details.text= ProgramRepo.programs[id-1].calories_burned
        program_exercises_details.text= ProgramRepo.programs[id-1].number_of_exercises
        programDetailsImage.setImageResource(ProgramRepo.programs[id-1].image)

    }
}
