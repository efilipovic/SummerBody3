package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import enafilipovic.ferit.summerbody.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_signin.setOnClickListener{
            startActivity(Intent(this,
                LoginActivity::class.java))
        }
        label_sign_up.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
    }
}
