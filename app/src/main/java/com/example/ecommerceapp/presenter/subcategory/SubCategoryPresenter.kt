package com.example.ecommerceapp.presenter.subcategory

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.SubCategoryListResponse
import com.example.ecommerceapp.model.remote.volleyhandler.SubCategoryVolleyHandler

class SubCategoryPresenter(private val volleyHandler: SubCategoryVolleyHandler, private val subCategoryView: SubCategoryMVP.SubCategoryView): SubCategoryMVP.SubCategoryPresenter {
    override fun loadSubCategoryList(categoryId: String): String {
        subCategoryView.onLoad(true)
        val message = volleyHandler.callSubCategoryListApi(categoryId,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    subCategoryView.setResult(data as SubCategoryListResponse)
                    subCategoryView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    subCategoryView.onLoad(false)
                }
            })
        return message
    }


}