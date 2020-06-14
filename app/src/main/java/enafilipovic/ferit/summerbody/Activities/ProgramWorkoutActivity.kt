package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import enafilipovic.ferit.summerbody.R
import enafilipovic.ferit.summerbody.Repositories.WorkoutRepo
import kotlinx.android.synthetic.main.activity_program_details.*
import kotlinx.android.synthetic.main.activity_program_details.back_arrow
import kotlinx.android.synthetic.main.activity_program_exercises.*

class ProgramWorkoutActivity : AppCompatActivity() {

    companion object {
        const val KEY_PROGRAM_ID: String = "KEY_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_exercises)

        back_arrow.setOnClickListener{
            startActivity(Intent(this, ProgramDetailsActivity::class.java))
            finish()
        }

     //   button_startworkout.setOnClickListener{
            //updateUI()
   //     }

        setUpUI(intent.getIntExtra(KEY_PROGRAM_ID,0))
    }

    private fun setUpUI(id: Int){
       // val workout_id=intent.getIntExtra(KEY_ID,0)
        title_program_workout.text=WorkoutRepo.workouts[id-1].workouts[0].workout_title
        workout_item_image.setImageResource(WorkoutRepo.workouts[id-1].workouts[0].workout_image)
        workout_item_description.text=WorkoutRepo.workouts[id-1].workouts[0].workout_description
    }

    private fun updateUI(id: Int, workout_id:Int){
      //TODO
    }
}
