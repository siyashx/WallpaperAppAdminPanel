package com.rangesun.a4kfreshwallpaperadmin.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rangesun.a4kfreshwallpaperadmin.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        //click privacy policy
        binding.privacyPolicy.setOnClickListener {
            val privacyPolicy = "https://rangesun.com/?page_id=985"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(privacyPolicy)
            startActivity(intent)
        }

        //click contact us
        binding.contactUs.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("info@rangesun.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Contact us")
                putExtra(Intent.EXTRA_TEXT, "")
            }
                startActivity(intent)
            }

        return binding.root

    }
}