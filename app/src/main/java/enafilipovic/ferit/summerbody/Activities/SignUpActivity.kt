package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import enafilipovic.ferit.summerbody.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()

        button_signup.setOnClickListener {
            signUpUser()
        }

        back_arrow_sign_up.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        label_login_here.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
      private  fun signUpUser(){
            if(reg_username.editText?.text.toString().isEmpty()){
                reg_username.error = getString(R.string.error_empty_email)
                return
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(reg_username.editText?.text.toString()).matches()){
                reg_username.error = getString(R.string.error_mail_format)
                return
            }
            if(reg_password.editText?.text.toString().isEmpty()){
                reg_password.error = getString(R.string.error_empty_pass)
                return

            }
          auth.createUserWithEmailAndPassword(reg_username.editText?.text.toString(), reg_password.editText?.text.toString())
              .addOnCompleteListener(this) { task ->
                  if (task.isSuccessful) {
                      val user = auth.currentUser
                      user!!.sendEmailVerification()
                          .addOnCompleteListener { task ->
                              if (task.isSuccessful) {
                                  startActivity(Intent(this,
                                      LoginActivity::class.java))
                                  finish()
                              }
                          }

                  } else {
                      // If sign in fails, display a message to the user.
                      Toast.makeText(baseContext, "Sign up failed. please check your inputs.",
                          Toast.LENGTH_SHORT).show()
                  }
              }
        }

}
