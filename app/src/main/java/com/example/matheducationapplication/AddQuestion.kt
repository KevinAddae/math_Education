package com.example.matheducationapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.matheducationapplication.Model.MathEducationDatabase
import com.example.matheducationapplication.Model.Question
import org.w3c.dom.Text

class AddQuestion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)
    }

    fun AddBtn(view: View){
        val db = MathEducationDatabase(this)
        var q: Question = Question(findViewById<TextView>(R.id.questionIdTxt).text.toString(),
            findViewById<TextView>(R.id.typeTxt).text.toString(), findViewById<TextView>(R.id.QuestionTxt).text.toString(),
            findViewById<TextView>(R.id.answerTxt).text.toString(),findViewById<TextView>(R.id.incorrect1Txt).text.toString(),
            findViewById<TextView>(R.id.incorrect2Txt).text.toString(),findViewById<TextView>(R.id.incorrect3).text.toString())
        db.addQuestion(q)

        findViewById<TextView>(R.id.responseTxt).text = "Question Has been added."
    }
}