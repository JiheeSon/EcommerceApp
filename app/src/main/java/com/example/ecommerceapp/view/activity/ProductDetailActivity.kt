package com.example.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.R
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
    private var amount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.extras?.get(PRODUCT_ID).toString()
        presenter = ProductDetailPresenter(ProductVolleyHandler(this), this)

        getProductDetail(productId)
        setUpAmountCounter()
    }

    private fun setUpAmountCounter() {
        binding.apply {
            btnAddToCart.setOnClickListener {
                TODO("add to cart activity")
            }
        }
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
        for (specification in specifications) {
            val row = TableRow(applicationContext)
            val title = TextView(applicationContext)
            val detail = TextView(applicationContext)

            val layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            layoutParams.setMargins(0,0,0,15)

            title.layoutParams = layoutParams
            detail.layoutParams = layoutParams

            title.text = specification.title
            title.textSize = 15F
            detail.text = specification.specification
            detail.textSize = 15F
            detail.setTextColor(getResources().getColor(R.color.purple_700))

            row.addView(title)
            row.addView(detail)
            binding.tableLayoutSpecification.addView(row)
        }

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