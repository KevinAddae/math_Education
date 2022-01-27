package com.example.matheducationapplication.Model

data class Question(var questionID: String, var topic:String, var question: String,
                var answer: String, var incorrect1: String, var incorrect2: String, var incorrect3: String){
}