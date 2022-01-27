package com.example.matheducationapplication.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.RowId
import java.sql.SQLData

private val DatabaseName = "MathEducationDatabase.db"
private val ver : Int = 1

class MathEducationDatabase(context:Context) : SQLiteOpenHelper(context, DatabaseName,null,ver) {

    // Student Table
    public val studentTableName = "Student"
    public val studentID = "StudentID"
    public val column_Firstname = "Firstname"
    public val column_Lastname = "Lastname"
    public val column_TestDate = "TestDate"
    public val column_Grade = "Grade"

    // Question Table
    val questionTableName = "Question"
    val questionID = "QuestionID"
    val column_Topic = "Topic"
    val column_Question = "Question"
    val column_answer = "Answer"
    val column_Incorrect1 = "Incorrect1"
    val column_Incorrect2 = "Incorrect2"
    val column_Incorrect3 = "Incorrect3"

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            var sqlCreateStatment: String = "CREATE TABLE " + studentTableName + " ( " + studentID +
                    " TEXT PRIMARY KEY " + column_Firstname + " TEXT " + column_Lastname + " TEXT " +
                    column_TestDate + " TEXT " + column_Grade + " INTEGER )"

            db?.execSQL(sqlCreateStatment)

            sqlCreateStatment = " CREATE TABLE " + questionTableName + " ( " + questionID +
                    " TEXT PRIMARY KEY " + column_Topic + " TEXT " + column_Question + " TEXT " +
                    column_answer + " TEXT " + column_Incorrect1 + " TEXT " + column_Incorrect2 +
                    " TEXT " + column_Incorrect3 + " TEXT ) "

            db?.execSQL(sqlCreateStatment)
        } catch (e: SQLException) {

        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addStudent(student: Student): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(studentID, student.studentId)
        cv.put(column_Firstname, student.firstname)
        cv.put(column_Lastname, student.lastname)
        cv.put(column_TestDate, student.testDate)
        cv.put(column_Grade, student.grade)

        val achieved = db.insert(studentTableName, null, cv)
        db.close()
        return achieved != -1L

    }

    fun deleteStudent(student: Student): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val result = db.delete(studentTableName, "$studentID = ${student.studentId}", null) == 1
        db.close()
        return result
    }

    fun updateStudent(student: Student): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(studentID, student.studentId)
        cv.put(column_Firstname, student.firstname)
        cv.put(column_Lastname, student.lastname)
        cv.put(column_TestDate, student.testDate)
        cv.put(column_Grade, student.grade)

        val result = db.update(studentTableName, cv, "$studentID = ${student.studentId}", null) == 1
        db.close()
        return result

    }

    fun getStudent(id: String): Student {
        val db: SQLiteDatabase = this.readableDatabase
        val sqlQuery = "SELECT * FROM $studentTableName"

        val cursor: Cursor = db.rawQuery(sqlQuery, null)
        if (cursor.moveToFirst()) {
            db.close()
            return Student(
                cursor.getString(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getInt(4)
            )
        } else {
            db.close()
            return Student(
                "id Not found", "Firstname not found", "Lastname not found",
                "Date not found", 0
            )
        }
    }
    fun addQuestion(question: Question): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(questionID, question.questionID)
        cv.put(column_Topic, question.topic)
        cv.put(column_Question, question.question)
        cv.put(column_answer, question.answer)
        cv.put(column_Incorrect1, question.incorrect1)
        cv.put(column_Incorrect2, question.incorrect2)
        cv.put(column_Incorrect3, question.incorrect3)


        val achieved = db.insert(questionTableName, null, cv)
        db.close()
        return achieved != -1L

    }

    fun getQuestion(): ArrayList<Question> {
        val quest = ArrayList<Question>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlQuery = "SELECT * FROM $questionTableName"

        val cursor: Cursor = db.rawQuery(sqlQuery, null)
        if (cursor.moveToFirst()) {
            do {

                val id: String = cursor.getString(0)
                val top: String = cursor.getString(1)
                val que: String = cursor.getString(2)
                val ans: String = cursor.getString(3)
                val inc1: String = cursor.getString(4)
                val inc2: String = cursor.getString(5)
                val inc3: String = cursor.getString(6)
                val b = Question(id, top, que, ans, inc1, inc2, inc3)
                quest.add(b)
            } while (cursor.moveToNext())

            cursor.close()
            db.close()


        }
        return quest
    }
}
            /**
            db.close()
            return Question(
            cursor.getString(0), cursor.getString(1), cursor.getString(2),
            cursor.getString(3), cursor.getString(4), cursor.getString(5),
            cursor.getString(6)
            )
            } else{
            db.close()
            return Question("id Not found", "Topic not found","Question not found",
            "Answer not found","incorrect1 not found","incorrect3 not found","incorrect3 not found")
            }**/

