package com.example.ecommerceapp.presenter.search

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.subcategory.SubCategoryProductListResponse
import com.example.ecommerceapp.model.remote.volleyhandler.ProductVolleyHandler

class SearchPresenter(private val volleyHandler: ProductVolleyHandler, private val searchView: SearchMVP.SearchView): SearchMVP.SearchPresenter {
    override fun searchProduct(query: String): String {
        searchView.onLoad(true)
        val message = volleyHandler.callSearchApi(query,
            object : OperationalCallback {
                override fun onSuccess(data: Any) {
                    searchView.setResult(data as SubCategoryProductListResponse)
                    searchView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    searchView.onLoad(false)
                }
            })
        return message
    }


}