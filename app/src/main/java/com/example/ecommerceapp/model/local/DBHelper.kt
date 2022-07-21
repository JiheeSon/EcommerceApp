package com.example.ecommerceapp.model.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.ecommerceapp.model.local.DBConstants.CREATE_USER_TABLE
import com.example.ecommerceapp.model.local.DBConstants.DB_NAME
import com.example.ecommerceapp.model.local.DBConstants.DB_VERSION

class DBHelper(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}