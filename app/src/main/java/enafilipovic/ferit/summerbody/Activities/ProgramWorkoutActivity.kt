package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import enafilipovic.ferit.summerbody.R
import enafilipovic.ferit.summerbody.Repositories.WorkoutRepo
import kotlinx.android.synthetic.main.activity_program_details.*
import kotlinx.android.synthetic.main.activity_program_details.back_arrow
import kotlinx.android.synthetic.main.activity_program_exercises.*

class ProgramWorkoutActivity : AppCompatActivity() {

    companion object {
        const val KEY_PROGRAM_ID: String = "KEY_ID"
    }


    var workout_id: Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_exercises)


        var program_id = intent.getIntExtra(KEY_PROGRAM_ID,0)

        back_arrow.setOnClickListener{
            startActivity(Intent(this, ProgramDetailsActivity::class.java))
            finish()
        }

     button_nextworkout.setOnClickListener{
        setUpUI(program_id,workout_id)
     }

        setUpUI(program_id,workout_id)
    }

   // private fun setUpUI(id: Int){
       // val workout_id=intent.getIntExtra(KEY_ID,0)
    //      title_program_workout.text=WorkoutRepo.workouts[id-1].workouts[0].workout_title
     //   workout_item_image.setImageResource(WorkoutRepo.workouts[id-1].workouts[0].workout_image)
      //  workout_item_description.text=WorkoutRepo.workouts[id-1].workouts[0].workout_description
     //   workout_id++
   // }

    private fun setUpUI(id: Int, w_id:Int){
        if(w_id == WorkoutRepo.workouts[id-1].workouts.size){
            Toast.makeText(baseContext, "Congrats, program finished!.",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }else {
            title_program_workout.text = WorkoutRepo.workouts[id - 1].workouts[w_id].workout_title
            workout_item_image.setImageResource(WorkoutRepo.workouts[id - 1].workouts[w_id].workout_image)
            workout_item_description.text =
                WorkoutRepo.workouts[id - 1].workouts[w_id].workout_description
            workout_id++
        }
    }
}
