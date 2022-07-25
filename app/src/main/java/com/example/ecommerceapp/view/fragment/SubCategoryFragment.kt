package com.example.ecommerceapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.FragmentSubCategoryBinding
import com.example.ecommerceapp.model.remote.data.Constants.SUB_CATEGORY_ID
import com.example.ecommerceapp.model.remote.data.subcategory.Product
import com.example.ecommerceapp.model.remote.data.subcategory.SubCategoryProductListResponse
import com.example.ecommerceapp.model.remote.volleyhandler.SubCategoryVolleyHandler
import com.example.ecommerceapp.presenter.subcategory.SubCategoryMVP
import com.example.ecommerceapp.presenter.subcategory.SubCategoryPresenter
import com.example.ecommerceapp.view.adapter.ProductAdapter

class SubCategoryFragment : Fragment(), SubCategoryMVP.SubCategoryView {
    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var subCategoryId: String
    private lateinit var presenter: SubCategoryPresenter
    private lateinit var productList: ArrayList<Product>
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubCategoryBinding.inflate(layoutInflater, container, false)
        subCategoryId = arguments?.getString(SUB_CATEGORY_ID).toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpEvents(view)
    }

    private fun setUpEvents(view: View) {
        presenter = SubCategoryPresenter(SubCategoryVolleyHandler(view.context), this)
        presenter.loadSubCategoryProducts(subCategoryId)
    }

    override fun setResult(data: Any) {
        productList = (data as SubCategoryProductListResponse).products
        adapter = ProductAdapter(productList)
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewProduct.adapter = adapter
        if (productList.isEmpty()) {
            binding.noItem.visibility = View.VISIBLE
        } else {
            binding.noItem.visibility = View.GONE
        }
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}