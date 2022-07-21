package com.example.ecommerceapp.model.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.ecommerceapp.model.remote.data.CartItem
import com.example.ecommerceapp.model.remote.data.Constants.PREF_CART
import com.example.ecommerceapp.model.remote.data.Constants.PREF_CHECKOUT_ADDRESS
import com.example.ecommerceapp.model.remote.data.Constants.PREF_CHECKOUT_ADDRESS_TITLE
import com.example.ecommerceapp.model.remote.data.Constants.PREF_CHECKOUT_PAYMENT
import com.example.ecommerceapp.model.remote.data.Constants.PREF_EMAIL
import com.example.ecommerceapp.model.remote.data.Constants.PREF_FILE_NAME
import com.example.ecommerceapp.model.remote.data.Constants.PREF_MOBILE
import com.example.ecommerceapp.model.remote.data.Constants.PREF_NAME
import com.example.ecommerceapp.model.remote.data.Constants.PREF_USER_ID
import com.example.ecommerceapp.model.remote.data.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

fun getLocalUserData(encryptedSharedPrefs: SharedPreferences): User {
    return User(
        encryptedSharedPrefs.getString(PREF_USER_ID, "Not found"),
        encryptedSharedPrefs.getString(PREF_NAME, "Not found")!!,
        encryptedSharedPrefs.getString(PREF_MOBILE, "Not found")!!,
        encryptedSharedPrefs.getString(PREF_EMAIL, "Not found")!!,
        null
    )
}

fun deleteLocalUserData(encryptedSharedPrefs: SharedPreferences): Boolean {
    val editor = encryptedSharedPrefs.edit()
    editor.clear()
    return editor.commit()
}

fun addCartItemLocally(encryptedSharedPrefs: SharedPreferences, newItem: CartItem): Boolean {
    val cartJson = encryptedSharedPrefs.getString(PREF_CART, "")
    val gson = Gson()
    val token: TypeToken<MutableList<CartItem>> = object : TypeToken<MutableList<CartItem>>() {}
    var newList: MutableList<CartItem>? = gson.fromJson(cartJson, token.type)
    var added = false
    if (newList == null) {
        newList = mutableListOf()
        newList.add(newItem)
    } else {
        for (i in newList.indices) {
            if (newList[i].productId == newItem.productId) {
                val copy = CartItem(
                    newItem.productId,
                    newItem.productName,
                    newItem.productDescription,
                    newItem.productPrice,
                    newItem.productImage,
                    newItem.amount + newList[i].amount
                )
                newList[i] = copy
                added = true
            }
        }
        if (!added) newList.add(newItem)
    }

    val editor = encryptedSharedPrefs.edit()
    editor.putString(PREF_CART, gson.toJson(newList, token.type))
    return editor.commit()
}

fun updateCartItemLocally(encryptedSharedPrefs: SharedPreferences, newItem: CartItem): Boolean {
    val cartJson = encryptedSharedPrefs.getString(PREF_CART, "")
    val gson = Gson()
    val token: TypeToken<MutableList<CartItem>> = object : TypeToken<MutableList<CartItem>>() {}
    val newList: MutableList<CartItem>? = gson.fromJson(cartJson, token.type)

    for (i in newList!!.indices) {
        if (newList[i].productId == newItem.productId) {
            newList[i] = newItem
        }
    }

    val editor = encryptedSharedPrefs.edit()
    editor.putString(PREF_CART, gson.toJson(newList, token.type))
    return editor.commit()
}

fun getCartItemLocally(encryptedSharedPrefs: SharedPreferences): MutableList<CartItem>? {
    val cartJson = encryptedSharedPrefs.getString(PREF_CART, "")
    val gson = Gson()
    val token: TypeToken<MutableList<CartItem>> = object : TypeToken<MutableList<CartItem>>() {}
    val list: MutableList<CartItem>? = gson.fromJson(cartJson, token.type)
    return list
}

fun removeCartItemLocally(encryptedSharedPrefs: SharedPreferences, productId: String): Boolean {
    val cartJson = encryptedSharedPrefs.getString(PREF_CART, "")
    val gson = Gson()
    val token: TypeToken<MutableList<CartItem>> = object : TypeToken<MutableList<CartItem>>() {}
    val newList: MutableList<CartItem>? = gson.fromJson(cartJson, token.type)

    for (i in newList!!.indices) {
        if (newList[i].productId == productId) {
            newList.remove(newList[i])
        }
    }
    val editor = encryptedSharedPrefs.edit()
    editor.putString(PREF_CART, gson.toJson(newList, token.type))
    return editor.commit()
}

fun storeCheckoutDataLocally(editor: SharedPreferences.Editor, tag: String, data: String): Boolean {
    editor.putString(tag, data)
    return editor.commit()
}

fun getSummaryDataLocally(encryptedSharedPrefs: SharedPreferences): Array<String?> {
    return arrayOf(
        encryptedSharedPrefs.getString(PREF_CHECKOUT_ADDRESS_TITLE, ""),
        encryptedSharedPrefs.getString(PREF_CHECKOUT_ADDRESS, ""),
        encryptedSharedPrefs.getString(PREF_CHECKOUT_PAYMENT, "")
    )
}

fun removedCartDataLocally(encryptedSharedPrefs: SharedPreferences): Boolean {
    val editor = encryptedSharedPrefs.edit()
    editor.apply {
        remove(PREF_CART)
        remove(PREF_CHECKOUT_ADDRESS)
        remove(PREF_CHECKOUT_ADDRESS_TITLE)
        remove(PREF_CHECKOUT_PAYMENT)
    }
    return editor.commit()
}