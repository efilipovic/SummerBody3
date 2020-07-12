package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ViewPortHandler
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class WeightTrackerActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
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

        back_arrow_wt.setOnClickListener{
            startActivity(Intent(this, ProgramViewActivity::class.java))
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser!=null){
            //Graph view
            getUserDocument().collection("weights")
                .orderBy("date", Query.Direction.ASCENDING)
                .addSnapshotListener { querysnapshot:QuerySnapshot?, ex ->
                    if (ex != null) {
                        Toast.makeText(baseContext, "Failed retrieving data.", Toast.LENGTH_SHORT).show()
                    }
                    val weights = ArrayList<Entry?>()

                    for (doc in querysnapshot!!.documents) {
                         val weight_entry = doc.toObject(WeightEntry::class.java)
                        weights.add(Entry( weight_entry!!.date.toFloat(),weight_entry.weight.toFloat()))
                    }

                // showChart
                    val weight_line = LineDataSet(weights,"My weight trend")
                    weight_line.setDrawValues((true))
                    weight_line.setDrawFilled(true)
                    weight_line.lineWidth = 3f
                    weight_line.color=getColor(R.color.colorPrimary)
                    weight_graph.xAxis.labelRotationAngle=60f
                    weight_line.fillColor=getColor(R.color.colorPrimary)
                    val xAxis=weight_graph.xAxis
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    weight_graph.data=LineData(weight_line)
                    xAxis.valueFormatter=(DateValueFormatter())
                    weight_graph.setTouchEnabled(true)
                    weight_graph.setPinchZoom(true)
                    weight_graph.description.text = "Time"
                    weight_graph.animateX(1800, Easing.EaseInExpo)
                }
           } else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    fun insertWeightData(v: View) {
        MaterialDialog.Builder(this)
            .title("Add Weight")
            .content("What's your weight today?")
            .inputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
            .input("Please enter weight in kg", "", false) { _, weight ->
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


    class DateValueFormatter() : ValueFormatter() {

        override fun getFormattedValue(value: Float): String {
            var time=value.toLong()
            var date = Date(time)
            val format = SimpleDateFormat("dd. MMM yyyy.")
            return format.format(date)
        }

    }

}
