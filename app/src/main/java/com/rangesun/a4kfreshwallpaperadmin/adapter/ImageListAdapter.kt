package com.rangesun.a4kfreshwallpaperadmin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.rangesun.a4kfreshwallpaperadmin.FinalWallpaper
import com.rangesun.a4kfreshwallpaperadmin.PagesActivity
import com.rangesun.a4kfreshwallpaperadmin.R
import com.rangesun.a4kfreshwallpaperadmin.databinding.ImageListItemBinding
import com.rangesun.a4kfreshwallpaperadmin.model.ImageList

class ImageListAdapter(private val requiredContext: Context, private val imageList: ArrayList<ImageList>) :
    RecyclerView.Adapter<ImageListAdapter.ImageListHolder>() {


    class ImageListHolder(val binding: ImageListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListHolder {
        val binding =
            ImageListItemBinding.inflate(LayoutInflater.from(requiredContext), parent, false)
        return ImageListHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageListHolder, position: Int) {

        //initialize firestore
        val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()

        //set image
        Glide.with(requiredContext)
            .load(imageList[position].link)
            .thumbnail(Glide.with(requiredContext).load(R.raw.loadinggrid))
            .into(holder.binding.imageList)

        //item view click
        holder.itemView.setOnClickListener {
            val intent = Intent(requiredContext, FinalWallpaper::class.java)
            intent.putExtra("link", imageList[position].link)
            requiredContext.startActivity(intent)
        }

        //click delete
        holder.binding.deleteImageList.setOnClickListener {
            val refRecent = firestore.collection(imageList[position].id.toString()).document(imageList[position].id.toString())
            refRecent.delete()
            requiredContext.startActivity(Intent(requiredContext, PagesActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}