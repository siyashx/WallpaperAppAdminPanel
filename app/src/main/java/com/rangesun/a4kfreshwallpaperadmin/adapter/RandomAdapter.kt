package com.rangesun.a4kfreshwallpaperadmin.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.ablanco.zoomy.Zoomy
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.rangesun.a4kfreshwallpaperadmin.FinalWallpaper
import com.rangesun.a4kfreshwallpaperadmin.PagesActivity
import com.rangesun.a4kfreshwallpaperadmin.R
import com.rangesun.a4kfreshwallpaperadmin.databinding.RandomItemBinding
import com.rangesun.a4kfreshwallpaperadmin.model.Random
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.util.*


class RandomAdapter(val requiredContext: Context, val randomList: ArrayList<Random>) :
    RecyclerView.Adapter<RandomAdapter.RandomHolder>() {

    private lateinit var firestore: FirebaseFirestore

    class RandomHolder(val binding: RandomItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomHolder {
        val binding = RandomItemBinding.inflate(LayoutInflater.from(requiredContext), parent, false)
        return RandomHolder(binding)
    }

    override fun onBindViewHolder(holder: RandomHolder, position: Int) {

        firestore = FirebaseFirestore.getInstance()


        //set image
        Glide.with(requiredContext)
        .load(randomList[position].link)
        .thumbnail(Glide.with(requiredContext).load(R.raw.loadinggrid))
        .into(holder.binding.randomImage)


        //zoomy builder
        Zoomy.Builder(requiredContext as Activity?)
            .target(holder.binding.randomImage)
            .enableImmersiveMode(false)
            .tapListener {
                val intent = Intent(requiredContext, FinalWallpaper::class.java)
                intent.putExtra("link", randomList[position].link)
                requiredContext.startActivity(intent)
            }
            ?.register()



    }

    override fun getItemCount(): Int {
        return randomList.size
    }


}