package com.example.a19bsit016_external1

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_view_students.*

class ViewStudents : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_students)

        var helper = DbHelper(applicationContext)
        var db: SQLiteDatabase = helper.writableDatabase
        var intent = getIntent()
        var userid = intent.getStringExtra("USER")
        var check = arrayOf(userid)
         var rs:Cursor = db.rawQuery("SELECT * FROM STUDENT1 WHERE USERID=?",check)

        rs.moveToFirst()
        if(rs.moveToNext()) {
            val rno = ArrayList<String>()
            val name = ArrayList<String>()
            rs.moveToFirst()
            while (rs.moveToNext()) {
                rno.add(rs.getString(1))
                name.add(rs.getString(2))
            }
            var myadapter = CustomAdapter(this,rno,name)
            listview.adapter=myadapter
        }
        else
        {
            Toast.makeText(this, "No Records Found", Toast.LENGTH_LONG).show()
            var intent = Intent(this,AddStudents::class.java)
            intent.putExtra("USER",userid)
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(1,101,1,"Add Student")
        menu?.add(1,102,1,"Logout")


        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            101->startActivity(Intent(this,AddStudents::class.java))
            102->startActivity(Intent(this,MainActivity::class.java))

        }
        return super.onOptionsItemSelected(item)
    }
}