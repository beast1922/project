package com.example.a19bsit016_external1

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var helper=DbHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        btnlogin.setOnClickListener {
        if(lid.text.toString() != "" && lpw.text.toString() != "")
        {
            var etuid = lid.text.toString()
            var etupw = lpw.text.toString()
            var check = arrayOf(etuid,etupw)
            var rs:Cursor = db.rawQuery("SELECT * FROM LOGIN1 WHERE USERID = ? AND PASSWORD = ?",check)
            rs.requery()
            if (rs.moveToNext()){
                var intent = Intent(this,AddTeachers::class.java)
                intent.putExtra("USER",etuid)
                startActivity(intent)

            }
            else
            {
                Toast.makeText(this,"Enter correct information",Toast.LENGTH_LONG).show()
            }

        }
        else if (lid.text.toString() == "" || lpw.text.toString() == "")
        {
            Toast.makeText(this,"Enter all information",Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(this,"Enter correct information",Toast.LENGTH_LONG).show()
        }

        }


        btnsignup.setOnClickListener {
            startActivity(Intent(this,Signupclass::class.java))
        }
    }
}