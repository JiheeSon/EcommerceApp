package com.example.ecommerceapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.FragmentSearchBinding
import com.example.ecommerceapp.model.remote.data.subcategory.SubCategoryProductListResponse
import com.example.ecommerceapp.model.remote.volleyhandler.ProductVolleyHandler
import com.example.ecommerceapp.presenter.search.SearchMVP
import com.example.ecommerceapp.presenter.search.SearchPresenter
import com.example.ecommerceapp.view.adapter.ProductAdapter

class SearchFragment : Fragment(), SearchMVP.SearchView {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var query: String
    private lateinit var presenter: SearchPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        query = arguments?.getString("query") ?: ""
        binding.editSearch.setText(query)

        setUpEvents(view)
    }

    private fun setUpEvents(view: View) {
        presenter = SearchPresenter(ProductVolleyHandler(view.context), this)
        presenter.searchProduct(query)

        binding.btnSearch.setOnClickListener {
            presenter.searchProduct(binding.editSearch.text.toString())
        }
        binding.btnClose.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun setResult(subCategoryProductListResponse: SubCategoryProductListResponse) {
        val adapter = ProductAdapter(subCategoryProductListResponse.products)
        binding.recyclerViewProduct.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewProduct.adapter = adapter
    }

    override fun setResult(message: String) {
        val a = 1
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.circularProgressBar.visibility = View.VISIBLE
        } else {
            binding.circularProgressBar.visibility = View.GONE
        }
    }
}