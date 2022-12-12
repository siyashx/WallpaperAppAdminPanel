package com.rangesun.a4kfreshwallpaperadmin.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.rangesun.a4kfreshwallpaperadmin.adapter.CategoriesAdapter
import com.rangesun.a4kfreshwallpaperadmin.databinding.FragmentCategoriesBinding
import com.rangesun.a4kfreshwallpaperadmin.model.Categories
@Suppress("DEPRECATION")
class CategoriesFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentCategoriesBinding

    //firestore
    private lateinit var fireStore: FirebaseFirestore

    private lateinit var requireContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext = context
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)

        //initialize firestore
        fireStore = FirebaseFirestore.getInstance()

        //firebase collection
        fireStore.collection("Categories").addSnapshotListener { value, _ ->
            val listCategories = arrayListOf<Categories>()
            val data = value?.toObjects(Categories::class.java)
            listCategories.addAll(data!!)

            //set has fixed true for recycler
            binding.categoryRecycler.setHasFixedSize(true)

            //set layout manager for recycler
            binding.categoryRecycler.layoutManager = LinearLayoutManager(requireContext)

            //initialize adapter
            val categoryAdapter = CategoriesAdapter(requireContext, listCategories)

            //set adapter for recycler
            binding.categoryRecycler.adapter = CategoriesAdapter(requireContext, listCategories)

            //set pull to refresh
            binding.pullToRefreshCategories.setOnRefreshListener {
                Handler().postDelayed({
                    binding.pullToRefreshCategories.isRefreshing = false
                    categoryAdapter.notifyDataSetChanged()
                }, 3000)
            }

        }





    return binding.root
}

}