package com.example.ecommerceapp.model.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.ecommerceapp.model.local.DBConstants.COL_EMAIL
import com.example.ecommerceapp.model.local.DBConstants.COL_MOBILE
import com.example.ecommerceapp.model.local.DBConstants.COL_NAME
import com.example.ecommerceapp.model.local.DBConstants.COL_PASSWORD
import com.example.ecommerceapp.model.local.DBConstants.COL_USERID
import com.example.ecommerceapp.model.local.DBConstants.TABLE_NAME
import com.example.ecommerceapp.model.remote.data.User

class UserDao (private val context: Context) {

    private val dbHelper = DBHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addUserToDatabase(user: User): Long {
        val contentValues = ContentValues()
        contentValues.apply {
            put(COL_USERID, user.user_id)
            put(COL_NAME, user.full_name)
            put(COL_MOBILE, user.mobile_no)
            put(COL_EMAIL, user.email_id)
        }

        return db.insert(TABLE_NAME, null, contentValues)
    }
}