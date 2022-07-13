package com.example.ecommerceapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentHomeBinding
import com.example.ecommerceapp.model.remote.data.Category
import com.example.ecommerceapp.model.remote.data.CategoryResponse
import com.example.ecommerceapp.model.remote.volleyhandler.CategoryVolleyHandler
import com.example.ecommerceapp.presenter.category.CategoryMVP
import com.example.ecommerceapp.presenter.category.CategoryPresenter
import com.example.ecommerceapp.view.adapter.CategoryAdapter

class HomeFragment : Fragment(), CategoryMVP.CategoryView {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: CategoryPresenter
    private lateinit var categoryList: ArrayList<Category>
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpEvents(view)
    }

    private fun setUpEvents(view: View) {
        presenter = CategoryPresenter(CategoryVolleyHandler(view.context), this)
        presenter.loadCategoryList()
    }

    override fun setResult(categoryResponse: CategoryResponse) {
        categoryList = categoryResponse.categories
        adapter = CategoryAdapter(categoryList)
        binding.rvCategory.layoutManager = GridLayoutManager(context, 2)
        binding.rvCategory.adapter = adapter
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}