package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import enafilipovic.ferit.summerbody.R
import enafilipovic.ferit.summerbody.Repositories.ProgramRepo
import kotlinx.android.synthetic.main.activity_program_details.*


class ProgramDetailsActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    companion object {
        const val KEY_ID: String = "KEY_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_details)
        auth = FirebaseAuth.getInstance()

        //val program_id = intent.getIntExtra(KEY_ID,0)

        back_arrow.setOnClickListener{
            startActivity(Intent(this, ProgramViewActivity::class.java))
            finish()
        }

     //   setUpUI(program_id)

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        val program_id = intent.getIntExtra(KEY_ID,0)

        button_startworkout.setOnClickListener{
            val Workoutintent = Intent(applicationContext, ProgramWorkoutActivity::class.java)
            // val Workoutintent = Intent(this,ProgramWorkoutActivity::class.java)
            Workoutintent.putExtra(ProgramWorkoutActivity.KEY_PROGRAM_ID, program_id)
            startActivity(Workoutintent)
            finish()
        }
        updateUI(program_id,currentUser)
    }
    private fun updateUI(id:Int,currentUser:FirebaseUser?){

        if(currentUser!=null) {
            title_program_details.text = ProgramRepo.programs[id - 1].title
            description_program.text = ProgramRepo.programs[id - 1].description
            program_duration_details.text = ProgramRepo.programs[id - 1].duration
            program_calories_details.text = ProgramRepo.programs[id - 1].calories_burned
            program_exercises_details.text = ProgramRepo.programs[id - 1].number_of_exercises
            programDetailsImage.setImageResource(ProgramRepo.programs[id - 1].image)
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
            }
}
