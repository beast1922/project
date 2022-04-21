package com.example.a19bsit016_external1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context):SQLiteOpenHelper(context,"STUDENTEXTERNAL",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE LOGIN1(USERID TEXT,PASSWORD TEXT)")
        db?.execSQL("CREATE TABLE TEACHER1(USERID TEXT,NAME TEXT,DESIGNATION TEXT,BRANCH TEXT,SUBJECT TEXT,MOBILE TEXT,EMAIL TEXT)")
        db?.execSQL("CREATE TABLE STUDENT1(USERID TEXT,RNO TEXT,NAME TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}