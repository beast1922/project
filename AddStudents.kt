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
import kotlinx.android.synthetic.main.activity_add_students.*

class AddStudents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_students)

        var helper = DbHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")

        btnsave.setOnClickListener {
            var check = arrayOf(userid, srno.text.toString())
            var rs: Cursor = db.rawQuery("SELECT * FROM STUDENT1 WHERE USERID=? AND RNO=?", check)

            rs.moveToFirst()
            if (rs.moveToNext()){
                Toast.makeText(this, "Already Entered", Toast.LENGTH_LONG).show()
            }
            else{
                var beforeinsert = rs.count
                var cv = ContentValues()
                cv.put("USERID", userid)
                cv.put("RNO", srno.text.toString())
                cv.put("NAME", sname.text.toString())
                db.insert("STUDENT1", null, cv)
                rs.requery()
                var afterinsert = rs.count
                if (afterinsert > beforeinsert) {
                    Toast.makeText(this, "Record Inserted Successfully", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Record Not Inserted", Toast.LENGTH_LONG).show()

                }
            }
        }

        btnallstu1.setOnClickListener {
            var intent=Intent(this,ViewStudents::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)
        }

        btnlogout1.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }



}