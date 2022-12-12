package com.rangesun.a4kfreshwallpaperadmin.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.rangesun.a4kfreshwallpaperadmin.adapter.CatalogAdapter
import com.rangesun.a4kfreshwallpaperadmin.adapter.RecentUploadedAdapter
import com.rangesun.a4kfreshwallpaperadmin.databinding.FragmentHomeBinding
import com.rangesun.a4kfreshwallpaperadmin.model.Catalogs
import com.rangesun.a4kfreshwallpaperadmin.model.RecentUploaded


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentHomeBinding

    //firestore
    private lateinit var fireStoreDB: FirebaseFirestore

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
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        //initialize firestore
        fireStoreDB = FirebaseFirestore.getInstance()


        //firestore collection for recent uploaded
        fireStoreDB.collection("Catalogs").addSnapshotListener { value, _ ->
            val listCatalogs = arrayListOf<Catalogs>()
            val data = value?.toObjects(Catalogs::class.java)
            listCatalogs.addAll(data!!)

            //set has fixed true for recent uploaded recycler
            binding.catalogsRecycler.setHasFixedSize(true)

            //linear layout manager
            val linearLayoutManager = LinearLayoutManager(requireContext, LinearLayoutManager.HORIZONTAL, true)
            linearLayoutManager.stackFromEnd = true

            binding.catalogsRecycler.layoutManager = linearLayoutManager

            //set adapter for recent uploaded recycler
            binding.catalogsRecycler.adapter = CatalogAdapter(requireContext, listCatalogs)


        }


        //firestore collection for recent uploaded
        fireStoreDB.collection("recentuploaded").addSnapshotListener { value, _ ->
            val listRecentUploaded = arrayListOf<RecentUploaded>()
            val data = value?.toObjects(RecentUploaded::class.java)
            listRecentUploaded.addAll(data!!)

            //set has fixed true for recent uploaded recycler
            binding.recentUploadedRecycler.setHasFixedSize(true)

            //set layout manager for recent uploaded recycler
            listRecentUploaded.reverse()
            binding.recentUploadedRecycler.layoutManager = GridLayoutManager(requireContext, 3)

            //set adapter for recent uploaded recycler
            binding.recentUploadedRecycler.adapter =
                RecentUploadedAdapter(requireContext, listRecentUploaded)


        }






        return binding.root
    }


}
