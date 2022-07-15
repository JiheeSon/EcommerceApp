package com.example.ecommerceapp.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ecommerceapp.model.remote.data.Constants.SUB_CATEGORY_ID
import com.example.ecommerceapp.model.remote.data.subcategory.Subcategory
import com.example.ecommerceapp.view.fragment.SubCategoryFragment


class SubCategoryViewPagerAdapter(fragmentActivity: FragmentActivity, private val subCategoryList: ArrayList<Subcategory>): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    override fun createFragment(position: Int): Fragment {

        return SubCategoryFragment().apply {
            val bundle = Bundle(1) // paramater capacity is number of data to pass
            bundle.putString(SUB_CATEGORY_ID, subCategoryList[position].subcategory_id) // key , value
            this.arguments = bundle
        }
    }

}