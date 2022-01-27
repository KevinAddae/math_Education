package com.example.matheducationapplication.Model

import android.content.Context

class Exam(context: Context){

    private val questionSet: ArrayList<Question>
    private val mathDBHelper = MathEducationDatabase(context)

    init {

    questionSet = mathDBHelper.getQuestion()

    }
    fun getTopic(num: Int): String{
        return questionSet.get(num).topic
    }

    fun getAnswer(num: Int): String{
        return questionSet.get(num).answer
    }

    fun getChoice1(num: Int): String{
        return questionSet.get(num).incorrect1
    }

    fun getChoice2(num: Int): String{
        return questionSet.get(num).incorrect2
    }

    fun getChoice3(num: Int): String{
        return questionSet.get(num).incorrect3
    }

    fun getQuestionSet(): ArrayList<Question>{
        return questionSet
    }
}