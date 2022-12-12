package com.rangesun.a4kfreshwallpaperadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.rangesun.a4kfreshwallpaperadmin.databinding.ActivityPagesBinding
import com.rangesun.a4kfreshwallpaperadmin.fragments.*

class PagesActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityPagesBinding

    //firestore
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()

        val settings = FirebaseFirestoreSettings.Builder()
            .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
            .build()
        firestore.firestoreSettings = settings

        //default fragment home fragment
        replaceFragment(HomeFragment())
        binding.navHome.setImageResource(R.drawable.nav_homed)

        //nav home
        binding.frameHome.setOnClickListener {
            //replace image
            binding.navHome.setImageResource(R.drawable.nav_homed)
            binding.navCategory.setImageResource(R.drawable.nav_category)
            binding.navRandom.setImageResource(R.drawable.nav_random)
            binding.navSettings.setImageResource(R.drawable.ic_setting)

            //replace fragment
            replaceFragment(HomeFragment())


        }

        //nav category
        binding.frameCategories.setOnClickListener {
            //replace image
            binding.navCategory.setImageResource(R.drawable.nav_categoried)
            binding.navHome.setImageResource(R.drawable.nav_home)
            binding.navRandom.setImageResource(R.drawable.nav_random)
            binding.navSettings.setImageResource(R.drawable.ic_setting)

            //replace fragment
            replaceFragment(CategoriesFragment())
        }

        //nav random
        binding.frameRandom.setOnClickListener {
            //change image
            binding.navRandom.setImageResource(R.drawable.nav_randomed)
            binding.navHome.setImageResource(R.drawable.nav_home)
            binding.navCategory.setImageResource(R.drawable.nav_category)
            binding.navSettings.setImageResource(R.drawable.ic_setting)

            //replace fragment
            replaceFragment(RandomFragment())
        }

        //nav random
        binding.frameSettings.setOnClickListener {
            //change image
            binding.navRandom.setImageResource(R.drawable.nav_random)
            binding.navHome.setImageResource(R.drawable.nav_home)
            binding.navCategory.setImageResource(R.drawable.nav_category)
            binding.navSettings.setImageResource(R.drawable.ic_settinged)

            //replace fragment
            replaceFragment(SettingsFragment())
        }



        //nav upload
        binding.frameAdd.setOnClickListener {
            //change image
            binding.navRandom.setImageResource(R.drawable.nav_random)
            binding.navHome.setImageResource(R.drawable.nav_home)
            binding.navCategory.setImageResource(R.drawable.nav_category)
            binding.navSettings.setImageResource(R.drawable.ic_setting)

            //replace fragment
            replaceFragment(UploadFragment())
        }

        //nav upload
        binding.frameReplace.setOnClickListener {
            //change image
            binding.navRandom.setImageResource(R.drawable.nav_random)
            binding.navHome.setImageResource(R.drawable.nav_home)
            binding.navCategory.setImageResource(R.drawable.nav_category)
            binding.navSettings.setImageResource(R.drawable.ic_setting)

            //replace fragment
            replaceFragment(ChangeFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        //replace fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentReplace, fragment)
        transaction.commit()
    }
}