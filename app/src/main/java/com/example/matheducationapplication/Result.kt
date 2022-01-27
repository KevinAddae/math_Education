package com.example.matheducationapplication

import android.icu.text.MessageFormat.format
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.text.format.DateFormat.format
import android.widget.TextView
import com.example.matheducationapplication.Model.MathEducationDatabase
import com.example.matheducationapplication.Model.Student
import java.lang.String.format
import java.sql.Time
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


class Result : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
         val db = MathEducationDatabase(this)

        var message = intent.getStringExtra(EXTRA_MESSAGE)
            var grade = intent.getIntExtra("sendGrade",0)
            val sdf = SimpleDateFormat("dd-mm-yyyy")
            var currentDate = sdf.format(Date())
        var stu: Student = Student(message.toString(), message?.substring(0,message!!.indexOf(" ")).toString(),
            message?.substring(message.indexOf(" ")).toString(),currentDate.toString(),grade)
        db.addStudent(stu)
            var s =  db.getStudent(stu.studentId).firstname.toString()
        findViewById<TextView>(R.id.resultTxt).text = "${stu.firstname} ${stu.lastname} \n Grade: ${stu.grade}/14 \n Date: ${stu.testDate}"
    }
}