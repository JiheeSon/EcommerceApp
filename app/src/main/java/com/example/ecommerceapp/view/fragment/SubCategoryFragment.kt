package com.example.ecommerceapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentSubCategoryBinding
import com.example.ecommerceapp.model.remote.data.SubCategoryListResponse
import com.example.ecommerceapp.presenter.subcategory.SubCategoryMVP

class SubCategoryFragment : Fragment(), SubCategoryMVP.SubCategoryView {
    private lateinit var binding: FragmentSubCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setResult(subCategoryListResponse: SubCategoryListResponse) {
        TODO("Not yet implemented")
    }

    override fun onLoad(isLoading: Boolean) {
        TODO("Not yet implemented")
    }
}