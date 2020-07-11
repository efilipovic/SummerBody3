package enafilipovic.ferit.summerbody.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Half.toFloat
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.afollestad.materialdialogs.MaterialDialog
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import enafilipovic.ferit.summerbody.Adapters.WeightEntryAdapter
import enafilipovic.ferit.summerbody.Models.MeasurementEntry
import enafilipovic.ferit.summerbody.Models.WeightEntry
import enafilipovic.ferit.summerbody.R
import kotlinx.android.synthetic.main.activity_measurement_tracker.*
import kotlinx.android.synthetic.main.activity_weight_tracker.*
import kotlinx.android.synthetic.main.measurements_dialog.*
import kotlinx.android.synthetic.main.measurements_dialog.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MeasurementTrackerActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var lineChart: LineChart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measurement_tracker)
        auth = FirebaseAuth.getInstance()
        lineChart = findViewById(R.id.measurement_graph)

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
            getUserDocument().collection("measurements")
                .orderBy("date", Query.Direction.ASCENDING)
                .addSnapshotListener { querysnapshot: QuerySnapshot?, ex ->
                    if (ex != null) {
                        return@addSnapshotListener
                    }
                    val measurements_waist = ArrayList<Entry?>()
                    val measurements_hips = ArrayList<Entry?>()
                    val measurements_breast = ArrayList<Entry?>()

                   for (doc in querysnapshot!!.documents) {
                        val measurement_entry = doc.toObject(MeasurementEntry::class.java)
                        measurements_waist.add(Entry( measurement_entry!!.date.toFloat(),measurement_entry.waist_circ.toFloat()))
                        measurements_hips.add(Entry( measurement_entry!!.date.toFloat(),measurement_entry.hips_circ.toFloat()))
                        measurements_breast.add(Entry( measurement_entry!!.date.toFloat(),measurement_entry.breast_circ.toFloat()))
                    }
                     var lines=LineData()
                    // showChart
                    val hips_line = LineDataSet(measurements_hips,"My hips trend")
                    hips_line.setDrawValues((true))
                    hips_line.lineWidth = 3f
                    hips_line.color=Color.BLACK
                    lines.addDataSet(hips_line)

                    // int color = ContextCompat.getColor(context, R.color.chart_color);

                    val waist_line = LineDataSet(measurements_waist,"My waist trend")
                    waist_line.setDrawValues((true))
                    waist_line.color=getColor(R.color.colorPrimary)
                    waist_line.lineWidth = 3f
                    lines.addDataSet(waist_line)

                    val breast_line = LineDataSet(measurements_breast,"My breast trend")
                    breast_line.setDrawValues((true))
                    breast_line.color=Color.GRAY
                    breast_line.lineWidth = 3f
                    lines.addDataSet(breast_line)


                    measurement_graph.xAxis.labelRotationAngle=60f
                    val xAxis=measurement_graph.xAxis
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    measurement_graph.data= lines
                    xAxis.valueFormatter=(DateValueFormatter())
                    measurement_graph.setTouchEnabled(true)
                    measurement_graph.setPinchZoom(true)
                    measurement_graph.description.text = "Time"
                    measurement_graph.animateX(1800, Easing.EaseInExpo)
                }
        } else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
    fun insertMeasurementData(v: View) {
      val measurementDialogView = LayoutInflater.from(this).inflate(R.layout.measurements_dialog,null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(measurementDialogView)
            .setTitle("Please enter your measurements for today!")
        val  mAlertDialog = mBuilder.show()
        measurementDialogView.btn_add_measur.setOnClickListener{
            mAlertDialog.dismiss()
            //get text from EditTexts of custom layout
            val hips = measurementDialogView.hips.text.toString()
            val waist = measurementDialogView.waist.text.toString()
            val breast = measurementDialogView.breast.text.toString()
            getUserDocument().collection("measurements")
                .add( MeasurementEntry(Date().time,waist.toDouble(),hips.toDouble(),breast.toDouble()))
        }


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
