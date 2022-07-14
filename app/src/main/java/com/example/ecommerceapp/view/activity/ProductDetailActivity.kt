package com.example.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.ActivityProductDetailBinding
import com.example.ecommerceapp.model.remote.data.Constants.PRODUCT_ID
import com.example.ecommerceapp.model.remote.data.product.*
import com.example.ecommerceapp.model.remote.volleyhandler.ProductVolleyHandler
import com.example.ecommerceapp.presenter.productdetail.ProductDetailMVP
import com.example.ecommerceapp.presenter.productdetail.ProductDetailPresenter
import com.example.ecommerceapp.view.adapter.ProductDetailImageViewPagerAdapter
import com.example.ecommerceapp.view.adapter.ReviewAdapter

class ProductDetailActivity : AppCompatActivity(),ProductDetailMVP.ProductDetailView {
    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var presenter: ProductDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.extras?.get(PRODUCT_ID).toString()
        presenter = ProductDetailPresenter(ProductVolleyHandler(this), this)

        getProductDetail(productId)
    }

    private fun getProductDetail(productId: String) {
        presenter.getProductDetail(productId)
    }

    override fun setResult(data: Any, successed: Boolean) {
        if (successed) {
            val productDetail = (data as ProductDetailResponse).product
            setUpViews(productDetail)
        }
    }

    private fun setUpViews(productDetail: ProductX) {
        binding.apply {
            textProductName.text = productDetail.product_name
            val price = "$ " + productDetail.price
            textProductPrice.text = price
            textProductDescription.text = productDetail.description
            ratingBarProduct.rating = productDetail.average_rating.toFloat()

            setUpImageViewPager(productDetail.images)
            setUpReviewRecyclerView(productDetail.reviews)
            setUpSpecificationTable(productDetail.specifications)
        }
    }

    private fun setUpSpecificationTable(specifications: ArrayList<Specification>) {

    }

    private fun setUpReviewRecyclerView(reviews: ArrayList<Review>) {
        val adapter = ReviewAdapter(reviews)
        binding.recyclerViewReview.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewReview.adapter = adapter
    }

    private fun setUpImageViewPager(images: ArrayList<Image>) {
        val adapter = ProductDetailImageViewPagerAdapter(images)
        binding.viewPagerImageProduct.adapter = adapter
        binding.circleIndicator.setViewPager(binding.viewPagerImageProduct)
    }

    override fun onLoad(isLoading: Boolean) {
        Log.i("tag", isLoading.toString())
    }
}