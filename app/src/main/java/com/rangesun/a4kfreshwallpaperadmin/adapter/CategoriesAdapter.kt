package com.rangesun.a4kfreshwallpaperadmin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.rangesun.a4kfreshwallpaperadmin.ImageListActivity
import com.rangesun.a4kfreshwallpaperadmin.PagesActivity
import com.rangesun.a4kfreshwallpaperadmin.R
import com.rangesun.a4kfreshwallpaperadmin.databinding.CategoriesItemBinding
import com.rangesun.a4kfreshwallpaperadmin.model.Categories

class CategoriesAdapter(private val requireContext: Context, private val categoriesList: ArrayList<Categories>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    class CategoriesHolder(val binding: CategoriesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val binding = CategoriesItemBinding.inflate(LayoutInflater.from(requireContext), parent, false)
        return CategoriesHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {

        //initialize firestore
        val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()


        //set image
        Glide.with(requireContext)
            .load(categoriesList[position].link)
            .thumbnail(Glide.with(requireContext).load(R.raw.loadingcategory))
            .into(holder.binding.categoriesImage)

        //item click listener
        holder.itemView.setOnClickListener {
            //intent data image list activity

            when(categoriesList[position].id) {
                "Abstract" -> {
                    //abstract
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Abstract")
                    requireContext.startActivity(intent)

                    //click delete
                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Abstract")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Amoled" -> {
                    //amoled
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Amoled")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Amoled")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Anime" -> {
                    //anime
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Anime")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Anime")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Exclusive" -> {
                    //exclusive
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Exclusive")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Exclusive")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Games" -> {
                    //amoled
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Games")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Games")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Gradient" -> {
                    //gradient
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Gradient")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Gradient")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Minimal" -> {
                    //minimal
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Minimal")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Minimal")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Nature" -> {
                    //nature
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Nature")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Nature")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Shapes" -> {
                    //shapes
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Shapes")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Shapes")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Shows" -> {
                    //shows
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Shows")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Shows")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Sports" -> {
                    //sports
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Sports")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Sports")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Stocks" -> {
                    //stocks
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Stocks")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Stocks")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }

                "Superheroes" -> {
                    //superheroes
                    val intent = Intent(requireContext, ImageListActivity::class.java)
                    intent.putExtra("id", "Superheroes")
                    requireContext.startActivity(intent)

                    //click delete

                    holder.binding.categoryDelete.setOnClickListener {
                        val refRecent = firestore.collection("Categories").document("Superheroes")
                        refRecent.delete()
                        requireContext.startActivity(Intent(requireContext, PagesActivity::class.java))
                    }
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}