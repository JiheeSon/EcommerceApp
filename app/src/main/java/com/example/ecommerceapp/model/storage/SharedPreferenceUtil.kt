package com.example.ecommerceapp.model.storage

import android.content.Context
import android.content.SharedPreferences
import android.provider.Telephony
import android.widget.Toast
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.ecommerceapp.model.remote.data.Constants.PREF_EMAIL
import com.example.ecommerceapp.model.remote.data.Constants.PREF_FILE_NAME
import com.example.ecommerceapp.model.remote.data.Constants.PREF_MOBILE
import com.example.ecommerceapp.model.remote.data.Constants.PREF_NAME
import com.example.ecommerceapp.model.remote.data.Constants.PREF_USER_ID

fun getEncryptedPrefs(context: Context): SharedPreferences {
    val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    val encryptedSharedPrefs = EncryptedSharedPreferences.create(
        PREF_FILE_NAME,
        mainKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )
    return encryptedSharedPrefs
}

fun isLoggedIn(encryptedSharedPrefs: SharedPreferences): Boolean {
    return encryptedSharedPrefs.contains(PREF_EMAIL)
}

fun storeLoginDataLocally(editor: SharedPreferences.Editor, userId: String, fullName: String, mobileNo: String, email: String): Boolean {
    editor.apply {
        putString(PREF_EMAIL, email)
        putString(PREF_MOBILE, mobileNo)
        putString(PREF_NAME, fullName)
        putString(PREF_USER_ID, userId)
        return commit()
    }
}