package com.example.ecommerceapp.presenter.productdetail

import com.example.ecommerceapp.model.remote.OperationalCallback
import com.example.ecommerceapp.model.remote.data.product.ProductDetailResponse
import com.example.ecommerceapp.model.remote.volleyhandler.ProductVolleyHandler

class ProductDetailPresenter(private val volleyHandler: ProductVolleyHandler, private val productDetailView: ProductDetailMVP.ProductDetailView): ProductDetailMVP.ProductDetailPresenter {

    override fun getProductDetail(productId: String): String {
        productDetailView.onLoad(true)
        val message = volleyHandler.callProductDetailApiCall(productId,
            object : OperationalCallback{
                override fun onSuccess(data: Any) {
                    productDetailView.setResult(data as ProductDetailResponse, true)
                    productDetailView.onLoad(false)
                }

                override fun onFailure(message: String) {
                    productDetailView.setResult(message, false)
                    productDetailView.onLoad(false)
                }

            })
        return message
    }


}