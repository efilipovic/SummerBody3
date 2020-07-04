package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import enafilipovic.ferit.summerbody.Adapters.WeightEntryAdapter
import enafilipovic.ferit.summerbody.Models.WeightEntry
import enafilipovic.ferit.summerbody.R

class WeightHistoryActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var adapter: WeightEntryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_history)
        auth = FirebaseAuth.getInstance()
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser!=null){
            val weightsView = findViewById<RecyclerView>(R.id.weight_view)
            weightsView.layoutManager = LinearLayoutManager(this)
            val query = getUserDocument().collection("weights")
                .orderBy("date", Query.Direction.DESCENDING)
                .limit(90)

///           Log.d("TAG", query.toString())
            val options = FirestoreRecyclerOptions.Builder<WeightEntry>()
                .setQuery(query, WeightEntry::class.java)
                .setLifecycleOwner(this)
                .build()
            adapter = WeightEntryAdapter(options)
            weightsView.adapter = adapter

        } else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    private fun getUserDocument(): DocumentReference {
        val db = FirebaseFirestore.getInstance()
        val users = db.collection("users")
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        return users.document(uid)
    }
}
