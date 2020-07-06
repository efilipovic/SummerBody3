package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.InputType
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.*
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import enafilipovic.ferit.summerbody.Adapters.WeightEntryAdapter
import enafilipovic.ferit.summerbody.Models.WeightEntry
import enafilipovic.ferit.summerbody.R
import kotlinx.android.synthetic.main.activity_weight_tracker.*
import java.util.*
import kotlin.collections.ArrayList


class WeightTrackerActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var adapter: WeightEntryAdapter? = null
    lateinit var lineChart: LineChart



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_tracker)
        auth = FirebaseAuth.getInstance()
        lineChart = findViewById(R.id.weight_graph)

        btn_weight_history.setOnClickListener{
            val Intent = Intent(applicationContext, WeightHistoryActivity::class.java)
            startActivity(Intent)
            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser!=null){
            //Graph view
            getUserDocument().collection("weights")
                .orderBy("date", Query.Direction.ASCENDING)
                .addSnapshotListener { querysnapshot:QuerySnapshot?, ex ->
                    if (ex != null) {
                        return@addSnapshotListener
                    }
                    val weights = ArrayList<Entry?>()

                    for (doc in querysnapshot!!.documents) {
                        val weight_entry = doc.toObject(WeightEntry::class.java)
                        var dateformat = (weight_entry!!.weight/10000)*1000
                        weights.add(Entry( dateformat.toFloat(),weight_entry.weight.toFloat()))
                    }
              //      Log.d("tag", "Current weight entries: $weights")

                  showChart(weights)
                }
           } else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun showChart(weights: ArrayList<Entry?>) {

        val vl = LineDataSet(weights,"My weight trend")

        vl.setDrawValues((false))
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.colorPrimary
        vl.fillAlpha = R.color.colorPrimary

        weight_graph.xAxis.labelRotationAngle=0f

        weight_graph.data=LineData(vl)
        weight_graph.axisRight.isEnabled = false
        weight_graph.xAxis.axisMaximum = 0.1f
        weight_graph.setTouchEnabled(true)
        weight_graph.setPinchZoom(true)
        weight_graph.description.text = "Time"
        weight_graph.animateX(1800, Easing.EaseInExpo)



    }

    fun insertWeightData(v: View) {
        MaterialDialog.Builder(this)
            .title("Add Weight")
            .content("What's your weight today?")
            .inputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
            .input("weight in kg", "", false) { _, weight ->
                getUserDocument()
                    .collection("weights")
                    .add( WeightEntry(weight.toString().toDouble(), Date().time)
                    )
            }.show()

    }
    private fun getUserDocument(): DocumentReference {
        val db = FirebaseFirestore.getInstance()
        val users = db.collection("users")
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        return users.document(uid)
    }

  

}
