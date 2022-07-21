package com.example.ecommerceapp.model.local

object DBConstants {
    const val DB_NAME = "ECommerce DB"
    const val DB_VERSION = 1

    const val TABLE_NAME = "User"

    const val COL_ID = "column"
    const val COL_USERID = "userID"
    const val COL_NAME = "name"
    const val COL_MOBILE = "mobileNo"
    const val COL_EMAIL = "email"
    const val COL_PASSWORD = "password"

    val CREATE_USER_TABLE = """CREATE TABLE user (
        $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $COL_USERID TEXT,
        $COL_NAME TEXT,
        $COL_MOBILE TEXT,
        $COL_EMAIL TEXT
                
        )""".trimIndent()
}