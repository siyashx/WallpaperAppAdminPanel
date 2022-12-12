package com.rangesun.a4kfreshwallpaperadmin

import android.annotation.SuppressLint
import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.rangesun.a4kfreshwallpaperadmin.databinding.ActivityFinalWalpaperBinding
import kotlinx.coroutines.*
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.Objects
import kotlin.random.Random

@Suppress("DEPRECATION")
class FinalWallpaper : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityFinalWalpaperBinding

    //set image wall list
    private val wallMethodsList = arrayOf("Set Home Screen", "Set Lock Screen", "Set Both")

    //set image index
    private var selectedSetWallIndex: Int = 0

    //selected item
    private var selectedItem = wallMethodsList[selectedSetWallIndex]

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalWalpaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //remove status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val url = intent.getStringExtra("link")

        //set url to url (method)
        val urlImage = URL(url)

        Glide.with(this).load(url).placeholder(R.drawable.walpaperapplogo).into(binding.finalWallpaper)

        //click download
        binding.btnDownload.setOnClickListener {


            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }

            GlobalScope.launch(Dispatchers.Main) {

                saveImage(result.await()!!)
            }


        }

        //click set wallpaper
        binding.btnSetWallpaper.setOnClickListener {

            MaterialAlertDialogBuilder(this)
                .setTitle("Apply")
                .setSingleChoiceItems(wallMethodsList, selectedSetWallIndex) { _, which ->
                    selectedSetWallIndex = which
                    selectedItem = wallMethodsList[which]
                    }
                .setPositiveButton("Apply") { _, _ ->
                    when (selectedItem) {
                        "Set Home Screen" -> {
                            binding.finalWallpaper.buildDrawingCache()
                            val bitmap : Bitmap? = binding.finalWallpaper.drawingCache

                            WallpaperManager.getInstance(this@FinalWallpaper).setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
                        }
                        "Set Lock Screen" -> {
                            binding.finalWallpaper.buildDrawingCache()
                            val bitmap : Bitmap? = binding.finalWallpaper.drawingCache

                            WallpaperManager.getInstance(this@FinalWallpaper).setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)

                        }
                        "Set Both" -> {
                            val result: Deferred<Bitmap?> = GlobalScope.async {
                                urlImage.toBitmap()
                            }

                            GlobalScope.launch(Dispatchers.Main) {

                                val wallpaperManager =
                                    WallpaperManager.getInstance(applicationContext)
                                wallpaperManager.setBitmap(result.await())
                            }

                        }
                    }

                }
                .show()

        }
    }



    //url method
    private fun URL.toBitmap() : Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: IOException) {
            null
        }
    }

    //save image
    @SuppressLint("InlinedApi")
    private fun saveImage(image: Bitmap) {

        val random1 = Random.nextInt(520985)
        val random2 = Random.nextInt(952663)

        val name = "FRESH-${random1 + random2}"

        val data: OutputStream
        try {
            val resolver = contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + "Fresh 4K Wallpaper")

            val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            data = resolver.openOutputStream(Objects.requireNonNull(imageUri)!!)!!
            image.compress(Bitmap.CompressFormat.JPEG, 100, data)
            Objects.requireNonNull(data)
            Toast.makeText(this, "Saved To Photos", Toast.LENGTH_SHORT).show()

        } catch (e : Exception) {
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show()

        }
    }
}