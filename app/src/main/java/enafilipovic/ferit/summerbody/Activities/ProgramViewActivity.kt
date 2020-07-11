package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import enafilipovic.ferit.summerbody.*
import enafilipovic.ferit.summerbody.Repositories.ProgramRepo
import kotlinx.android.synthetic.main.activity_programs.*


class ProgramViewActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var auth: FirebaseAuth
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programs)
        auth = FirebaseAuth.getInstance()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser!=null) {
            programView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            displayData()
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
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
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_weight -> {
                startActivity(Intent(this, WeightTrackerActivity::class.java))
            }
            R.id.nav_measurements -> {
                startActivity(Intent(this, MeasurementTrackerActivity::class.java))
            }
            R.id.nav_photos -> {
                startActivity(Intent(this, PhotoTrackerActivity::class.java))
            }
            R.id.nav_logoout -> {
                auth.signOut()
                Toast.makeText(this, "Session expired. please sign in again", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}


