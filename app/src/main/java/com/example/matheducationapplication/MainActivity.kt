package com.example.matheducationapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.TextView
import com.example.matheducationapplication.Model.MathEducationDatabase
import com.example.matheducationapplication.Model.Student
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startTest(view:View){
        val first = findViewById<TextView>(R.id.firstnameTxt)
        val sur = findViewById<TextView>(R.id.surnameTxt)
        val error1 = findViewById<TextView>(R.id.errorTxtV)

        if (first.text.toString().isEmpty() || sur.text.toString().isEmpty()){
            error1.text = "Please make sure to add both your first and last name"
        } else {
            val db = MathEducationDatabase(this)
            var studentName: String = first.text.toString() + "" + sur.text.toString()
             studentName = first.text.toString() + " " + sur.text.toString()
            val Intent1 = Intent(this,QuestionSet::class.java).apply {
                putExtra(EXTRA_MESSAGE, studentName)
            }
            startActivity(Intent1)
        }

    }
    fun addQuestionBtn(view: View){
        var intent = Intent(this,AddQuestion::class.java)
        startActivity(intent)
    }
}