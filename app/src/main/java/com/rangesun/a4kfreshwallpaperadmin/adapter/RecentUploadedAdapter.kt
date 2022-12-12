package com.rangesun.a4kfreshwallpaperadmin.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ablanco.zoomy.Zoomy
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.rangesun.a4kfreshwallpaperadmin.FinalWallpaper
import com.rangesun.a4kfreshwallpaperadmin.PagesActivity
import com.rangesun.a4kfreshwallpaperadmin.R
import com.rangesun.a4kfreshwallpaperadmin.databinding.RecentUploadedItemBinding
import com.rangesun.a4kfreshwallpaperadmin.model.RecentUploaded

class RecentUploadedAdapter(
    private val requiredContext: Context,
    private val recentUploadedList: ArrayList<RecentUploaded>

) : RecyclerView.Adapter<RecentUploadedAdapter.RecentUploadedHolder>() {

    class RecentUploadedHolder(val binding: RecentUploadedItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentUploadedHolder {
        val binding =
            RecentUploadedItemBinding.inflate(LayoutInflater.from(requiredContext), parent, false)
        return RecentUploadedHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentUploadedHolder, position: Int) {

        //initialize firestore
        val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()



        //set image
        Glide.with(requiredContext)
            .load(recentUploadedList[position].link)
            .thumbnail(Glide.with(requiredContext).load(R.raw.loadinggrid))
            .into(holder.binding.recentUploadedImage)

        //zoomy builder
        Zoomy.Builder(requiredContext as Activity?)
            .target(holder.binding.recentUploadedImage)
            .enableImmersiveMode(false)
            .tapListener {
                val intent = Intent(requiredContext, FinalWallpaper::class.java)
                intent.putExtra("link", recentUploadedList[position].link)
                requiredContext.startActivity(intent)
            }
            ?.register()

        //click delete
        holder.binding.deleteRecentUploaded.setOnClickListener {
            val refRecent = firestore.collection("recentuploaded").document(recentUploadedList[position].id.toString())
            refRecent.delete()
            requiredContext.startActivity(Intent(requiredContext, PagesActivity::class.java))
        }


    }

    override fun getItemCount(): Int {
        return recentUploadedList.size
    }
}