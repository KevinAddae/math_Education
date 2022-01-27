package com.example.matheducationapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.matheducationapplication.Model.Exam
import com.example.matheducationapplication.Model.Question
import java.util.*
import kotlin.collections.ArrayList


class QuestionSet : AppCompatActivity() {
    lateinit var questions: Exam
    var answerList: ArrayList<String> = ArrayList()
    var savedQuestionOrder: ArrayList<Question> = ArrayList()
    var randomChoice: ArrayList<String> = ArrayList()
    var questionNum: Int = 0
    var choices: ArrayList<String> = ArrayList()
    var groupOfChoices: ArrayList<ArrayList<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_set)

        questions = Exam(this)
        questions.getQuestionSet().shuffle()


        var tempArray: ArrayList<Question> = ArrayList(questions.getQuestionSet())
        var temp2: ArrayList<Question> = ArrayList()
        var i =0
        var x =0

        tempArray.forEach { s ->
            when {
                temp2.isEmpty() -> temp2.add(s)

                temp2.get(i).topic != s.topic -> {
                    temp2.add(s)
                    i+=1
                }
                savedQuestionOrder.isEmpty() -> savedQuestionOrder.add(s)
                savedQuestionOrder.get(x).topic != s.topic -> {
                    savedQuestionOrder.add(s)
                    x+=1

                }
            }
        }

        savedQuestionOrder.addAll(tempArray)
        savedQuestionOrder.shuffle()

        randomChoice.add(savedQuestionOrder.get(0).answer)
        randomChoice.add(savedQuestionOrder.get(0).incorrect1)
        randomChoice.add(savedQuestionOrder.get(0).incorrect2)
        randomChoice.add(savedQuestionOrder.get(0).incorrect3)

        randomChoice.shuffle()
        findViewById<TextView>(R.id.questionTV).text = savedQuestionOrder.get(0).question
        findViewById<RadioButton>(R.id.radio1).text = randomChoice.get(0)
        findViewById<RadioButton>(R.id.radio2).text = randomChoice.get(1)
        findViewById<RadioButton>(R.id.radio3).text = randomChoice.get(2)
        findViewById<RadioButton>(R.id.radio4).text = randomChoice.get(3)
        findViewById<TextView>(R.id.num).text = questionNum.toString()
    }

    fun nextBtnEvent(view:View){

        var choice1 = findViewById<RadioButton>(R.id.radio1)
        var choice2 = findViewById<RadioButton>(R.id.radio2)
        var choice3 = findViewById<RadioButton>(R.id.radio3)
        var choice4 = findViewById<RadioButton>(R.id.radio4)

        try {


            when {
                choice1.isChecked -> answerList.add(questionNum, choice1.text.toString())
                choice2.isChecked -> answerList.add(questionNum, choice2.text.toString())
                choice3.isChecked -> answerList.add(questionNum, choice3.text.toString())
                choice4.isChecked -> answerList.add(questionNum, choice4.text.toString())
                else -> findViewById<TextView>(R.id.errorRadTxt).text = "Please make sure to pick an option"
            }
        }

        catch (e: java.lang.IndexOutOfBoundsException){
            e.message
        }
        if (answerList.size > questionNum) {
            choices.add(choice1.text.toString())
            choices.add(choice2.text.toString())
            choices.add(choice3.text.toString())
            choices.add(choice4.text.toString())
            groupOfChoices.add(choices)

            randomChoice.clear()
            questionNum += 1
            randomChoice.add(savedQuestionOrder.get(questionNum).answer)
            randomChoice.add(savedQuestionOrder.get(questionNum).incorrect1)
            randomChoice.add(savedQuestionOrder.get(questionNum).incorrect2)
            randomChoice.add(savedQuestionOrder.get(questionNum).incorrect3)

            randomChoice.shuffle()
            findViewById<TextView>(R.id.questionTV).text =
                savedQuestionOrder.get(questionNum).question
            findViewById<RadioButton>(R.id.radio1).text = randomChoice.get(0)
            findViewById<RadioButton>(R.id.radio2).text = randomChoice.get(1)
            findViewById<RadioButton>(R.id.radio3).text = randomChoice.get(2)
            findViewById<RadioButton>(R.id.radio4).text = randomChoice.get(3)

            if (questionNum >= 14) {
                var i = 0
                var grade = 0
                answerList.forEach { s ->
                    if (savedQuestionOrder.get(i).answer.equals(s))
                        grade += 1
                }
                var message = intent.getStringExtra(EXTRA_MESSAGE.toString())
                val intent1 = Intent(this, Result::class.java).apply {
                    putExtra(EXTRA_MESSAGE, message)
                    putExtra("sendGrade", grade)
                }
                startActivity(intent1)


            }
            findViewById<TextView>(R.id.num).text = questionNum.toString()
        }
    }

}