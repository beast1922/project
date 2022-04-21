package com.example.a19bsit016_external1

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signupclass.*

class Signupclass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupclass)

        btnsignup2.setOnClickListener {
            if (sid.text.toString()== "" || spw.text.toString() == "" || spw2.text.toString() == ""){
                Toast.makeText(this,"Enter all information!",Toast.LENGTH_LONG).show()
            }
            else if (spw.text.toString() != spw2.text.toString()){
                Toast.makeText(this,"Enter Same Password!",Toast.LENGTH_LONG).show()
            }
            else{
                var helper=DbHelper(applicationContext)
                var db:SQLiteDatabase = helper.writableDatabase
                var check = arrayOf(sid.text.toString())
                var rs:Cursor=db.rawQuery("SELECT * FROM LOGIN1 WHERE USERID = ?",check)
                rs.requery()
                if (rs.moveToNext()){
                    Toast.makeText(this,"User Exist , try with new ID!",Toast.LENGTH_LONG).show()
                }
                else
                {
                    var beforeinsert = rs.count
                    var cv=ContentValues()
                    cv.put("USERID",sid.text.toString())
                    cv.put("PASSWORD",spw.text.toString())
                    db.insert("LOGIN1",null,cv)
                    rs.requery()
                    var afterinsert=rs.count
                    if (beforeinsert < afterinsert)
                    {
                        Toast.makeText(this,"User Created successfully",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    }
                    else{
                        Toast.makeText(this,"User not Created!",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}