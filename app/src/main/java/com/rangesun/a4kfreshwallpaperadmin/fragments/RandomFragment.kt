package com.rangesun.a4kfreshwallpaperadmin.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.rangesun.a4kfreshwallpaperadmin.adapter.RandomAdapter
import com.rangesun.a4kfreshwallpaperadmin.databinding.FragmentRandomBinding
import com.rangesun.a4kfreshwallpaperadmin.model.Random
import java.util.*

@Suppress("DEPRECATION")
class RandomFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentRandomBinding

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
        binding =
            FragmentRandomBinding.inflate(LayoutInflater.from(requireContext()), container, false)

        //initialize firestore
        fireStore = FirebaseFirestore.getInstance()

        //firebase collection
        fireStore.collection("recentuploaded").addSnapshotListener { value, _ ->
            val listRandom = arrayListOf<Random>()
            val data = value?.toObjects(Random::class.java)
            listRandom.addAll(data!!)

            //set has fixed true for recycler
            binding.randomRecycler.setHasFixedSize(true)

            //set layout manager for recycler
            binding.randomRecycler.layoutManager = GridLayoutManager(requireContext, 3)


            //set reverse for random list
            listRandom.shuffle()

            //initialize adapter
            val randomAdapter = RandomAdapter(requireContext, listRandom)

            //set adapter for recycler
            binding.randomRecycler.adapter = RandomAdapter(requireContext, listRandom)

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(binding.randomRecycler)

            //set pull to refresh
            binding.pullToRefreshRandom.setOnRefreshListener {
                Handler().postDelayed({
                    binding.pullToRefreshRandom.isRefreshing = false
                    randomAdapter.notifyDataSetChanged()
                }, 3000)
            }

        }



        return binding.root
    }

}