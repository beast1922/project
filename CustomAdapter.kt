package com.example.a19bsit016_external1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(
    var viewStudents: ViewStudents,
    var rno: ArrayList<String>,
    var name: ArrayList<String>

):BaseAdapter() {
    override fun getCount(): Int {
        return rno.size
    }

    override fun getItem(p0: Int): Any {
       return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var i = LayoutInflater.from(viewStudents).inflate(R.layout.listitems,p2,false)
        var rn = i.findViewById<TextView>(R.id.tvrnolist)
        var nm = i.findViewById<TextView>(R.id.tvnamelist)
        rn.text = rno[p0]
        nm.text = name[p0]
        return i
    }
}