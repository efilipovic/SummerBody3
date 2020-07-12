package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import enafilipovic.ferit.summerbody.R
import kotlinx.android.synthetic.main.activity_sign_in.*


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = FirebaseAuth.getInstance()
        label_sign_up.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        back_arrow.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        button_signin.setOnClickListener{
            Login()
        }
    }

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser!=null){
            if(currentUser.isEmailVerified){
            startActivity(Intent(this, ProgramViewActivity::class.java))
            finish()
            }else{
                Toast.makeText(baseContext, "Please verify your e-mail address.", Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private fun Login(){
        if(usernameField.editText?.text.toString().isEmpty()){
            usernameField.error = getString(R.string.error_empty_email)
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(usernameField.editText?.text.toString()).matches()){
            usernameField.error = getString(R.string.error_mail_format)
            return
        }
        if(passwordField.editText?.text.toString().isEmpty()){
            passwordField.error = getString(R.string.error_empty_pass)
            return

        }
        auth.signInWithEmailAndPassword(usernameField.editText?.text.toString(), passwordField.editText?.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }
}
