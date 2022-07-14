package com.example.ecommerceapp.presenter.category

import android.util.Log
import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.category.CategoryResponse
import com.example.ecommerceapp.model.remote.data.Constants.TAG_DEV
import com.example.ecommerceapp.model.remote.volleyhandler.CategoryVolleyHandler

class CategoryPresenter(private val volleyHandler: CategoryVolleyHandler, private val categoryView: CategoryMVP.CategoryView): CategoryMVP.CategoryPresenter {
    override fun loadCategoryList(): String {
        val message = volleyHandler.callProductCategoryApi(
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    categoryView.setResult(data as CategoryResponse)
                    categoryView.onLoad(false)
                }
                override fun onFailure(message: String) {
                    Log.i(TAG_DEV, message)
                    categoryView.onLoad(false)
                }
            }
        )
        return message
    }

}