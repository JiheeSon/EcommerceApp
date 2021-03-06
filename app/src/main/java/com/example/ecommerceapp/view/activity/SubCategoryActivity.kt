package com.example.ecommerceapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.ecommerceapp.databinding.ActivitySubCategoryBinding
import com.example.ecommerceapp.model.remote.data.Constants.CATEGORY_ID
import com.example.ecommerceapp.model.remote.data.subcategory.SubCategoryListResponse
import com.example.ecommerceapp.model.remote.data.subcategory.Subcategory
import com.example.ecommerceapp.model.remote.volleyhandler.SubCategoryVolleyHandler
import com.example.ecommerceapp.presenter.subcategory.SubCategoryMVP
import com.example.ecommerceapp.presenter.subcategory.SubCategoryPresenter
import com.example.ecommerceapp.view.adapter.SubCategoryViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SubCategoryActivity : AppCompatActivity(), SubCategoryMVP.SubCategoryView {
    private lateinit var binding: ActivitySubCategoryBinding
    private lateinit var presenter: SubCategoryPresenter
    private lateinit var subCategoryList: ArrayList<Subcategory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryId = intent.extras?.get(CATEGORY_ID) as String
        getSubCategoryList(categoryId)
    }

    private fun getSubCategoryList(categoryId: String) {
        presenter = SubCategoryPresenter(SubCategoryVolleyHandler(this), this)
        presenter.loadSubCategoryList(categoryId)
    }

    private fun setUpViewPager() {
        val adapter = SubCategoryViewPagerAdapter(this, subCategoryList)
        binding.viewPagerSubCategory.adapter = adapter
    }

    private fun setUpTabLayout() {
        TabLayoutMediator(binding.tabLayoutSubCategory, binding.viewPagerSubCategory) {
                tab, position ->
            tab.text = subCategoryList[position].subcategory_name
        }.attach()
    }

    override fun setResult(data: Any) {
        subCategoryList = (data as SubCategoryListResponse).subcategories
        if (subCategoryList.isEmpty()) {
            binding.noItem.visibility = View.VISIBLE
        } else {
            binding.noItem.visibility = View.GONE
        }
        setUpViewPager()
        setUpTabLayout()
    }

    override fun onLoad(isLoading: Boolean) {
        Log.i("tag", isLoading.toString())
    }
}