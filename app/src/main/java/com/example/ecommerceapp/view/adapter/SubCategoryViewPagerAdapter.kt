package com.example.ecommerceapp.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ecommerceapp.model.remote.data.Constants.SUB_CATEGORY_ID
import com.example.ecommerceapp.model.remote.data.Subcategory
import com.example.ecommerceapp.view.fragment.SubCategoryFragment


class SubCategoryViewPagerAdapter(fragmentActivity: FragmentActivity, private val subCategoryList: ArrayList<Subcategory>): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    override fun createFragment(position: Int): Fragment {
        //val fragment: Fragment = UserFragment() // Fragment 생성

//        val bundle = Bundle(1) // 파라미터는 전달할 데이터 개수
//
//        bundle.putString("subcategory_id", subCategoryList[position].subcategory_id) // key , value
//
//        fragment.arguments = bundle

        return SubCategoryFragment().apply {
            val bundle = Bundle(1) // 파라미터는 전달할 데이터 개수
            bundle.putString(SUB_CATEGORY_ID, subCategoryList[position].subcategory_id) // key , value
            this.arguments = bundle
        }
    }

}