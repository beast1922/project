package com.example.a19bsit016_external1

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_view_teachers.*

class ViewTeachers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_teachers)

        var helper = DbHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")
        var check = arrayOf(userid)
        var rs: Cursor = db.rawQuery("SELECT * FROM TEACHER1 WHERE USERID=?", check)
        rs.requery()

        if (rs.moveToNext()) {
            tname1.text = rs.getString(1)
            tdesignation1.text = rs.getString(2)
            tbranch1.text = rs.getString(3)
            tsubject1.text = rs.getString(4)
            tmobile1.text = rs.getString(5)
            temail1.text = rs.getString(6)

        }
        else {
            tname1.text = "No Record"
            tdesignation1.text = "No Record"
            tbranch1.text = "No Record"
            tsubject1.text = "No Record"
            tmobile1.text = "No Record"
            temail1.text = "No Record"


        }

        btnallstu.setOnClickListener {
            var intent = Intent(this,ViewStudents::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)
        }

        btnlogout.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        btnaddstu1.setOnClickListener {
            var intent = Intent(this,AddStudents::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)

        }
    }



}