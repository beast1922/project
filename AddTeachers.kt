package com.example.a19bsit016_external1

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_teachers.*

class AddTeachers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_teachers)

        var helper = DbHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid =intent.getStringExtra("USER")

        var check = arrayOf(userid)
        var rs:Cursor = db.rawQuery("SELECT * FROM TEACHER1 WHERE USERID=?",check)
        rs.requery()
        if (rs.moveToNext())
        {
            tname.setText(rs.getString(1).toString())
            tdesignation.setText(rs.getString(2))
            tbranch.setText(rs.getString(3))
            tsubject.setText(rs.getString(4))
            tmobile.setText(rs.getString(5))
            temail.setText(rs.getString(6))
        }
        else
        {
            tname.setHint("Enter name")
            tdesignation.setHint("Enter Designation")
            tbranch.setHint("Enter Course")
            tsubject.setHint("Enter Subject")
            tmobile.setHint("Enter Mobile No")
            temail.setHint("Enter Email")
        }

        btnupdate.setOnClickListener {
            if (tname.text.toString() != "" && tdesignation.text.toString() != "" && tbranch.text.toString() != "" && tsubject.text.toString() != "" && tmobile.text.toString() != "" && temail.text.toString() != "")
            {

                var cv = ContentValues()
                cv.put("USERID",userid)
                cv.put("NAME",tname.text.toString())
                cv.put("DESIGNATION",tdesignation.text.toString())
                cv.put("BRANCH",tbranch.text.toString())
                cv.put("SUBJECT",tsubject.text.toString())
                cv.put("MOBILE",tmobile.text.toString())
                cv.put("EMAIL",temail.text.toString())
                db.insert("TEACHER1",null,cv)
                rs.requery()
                Toast.makeText(this, "Teacher Information Updated", Toast.LENGTH_LONG).show()
                var intent = Intent(this,ViewTeachers::class.java)
                intent.putExtra("USER",userid)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Enter All details", Toast.LENGTH_LONG).show()
            }




        }

        btnhome.setOnClickListener {
            var intent = Intent(this,ViewTeachers::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)
        }

        btnaddstu.setOnClickListener {
            var intent = Intent(this,AddStudents::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)
        }




        }




}