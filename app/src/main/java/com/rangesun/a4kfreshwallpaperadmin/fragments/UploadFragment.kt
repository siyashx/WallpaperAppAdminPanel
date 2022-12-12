@file:Suppress("DEPRECATION")

package com.rangesun.a4kfreshwallpaperadmin.fragments

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.rangesun.a4kfreshwallpaperadmin.R
import com.rangesun.a4kfreshwallpaperadmin.databinding.FragmentUploadBinding
import com.rangesun.a4kfreshwallpaperadmin.model.ImageList
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class UploadFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentUploadBinding

    //pick image
    private val pickImage = 1

    //image uri
    private var imageUri: Uri? = null

    //set image wall list
    private val categoriesList = arrayOf(
        "Abstract", "Amoled", "Anime", "Exclusive", "Depth Effect", "Games",
        "Gradient", "Minimal", "Nature", "Shapes", "Shows", "Sports",
        "Stocks", "Superheroes"
    )

    private lateinit var requireContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext = context
    }

    //set image index
    private var selectedSetCategoryIndex: Int = 0

    //selected item
    private var selectedItem = categoriesList[selectedSetCategoryIndex]

    //firestore
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadBinding.inflate(layoutInflater, container, false)

        //text write disable for edit text
        binding.selectCategory.keyListener = null

        //initialize firestore
        firestore = FirebaseFirestore.getInstance()

        //click select image
        binding.selectImage.setOnClickListener {
            //call method
            selectImage()
        }

        //click select category
        binding.selectCategory.setOnClickListener {
            //call method
            selectCategory()
        }

        //check empty
        binding.uploadBtn.setOnClickListener {
            if (binding.selectCategory.hint.equals("Select Category...")) {
                Toast.makeText(requireContext, "Please select category!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                uploadImage()
            }
        }


        return binding.root
    }

    private fun selectImage() {
        Dexter.withContext(requireContext).withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    val intent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(
                        intent,
                        pickImage
                    )
                }

                override fun onPermissionRationaleShouldBeShown(
                    list: List<PermissionRequest>,
                    permissionToken: PermissionToken
                ) {
                    permissionToken.continuePermissionRequest()
                }
            }).check()
    }

    private fun selectCategory() {
        MaterialAlertDialogBuilder(requireContext)
            .setTitle("Apply")
            .setSingleChoiceItems(categoriesList, selectedSetCategoryIndex) { _, which ->
                selectedSetCategoryIndex = which
                selectedItem = categoriesList[which]
            }
            .setPositiveButton("Apply") { _, _ ->
                when (selectedItem) {
                    "Abstract" -> {
                        //set hint abstract
                        binding.selectCategory.hint = "Abstract"

                    }
                    "Amoled" -> {
                        //set hint amoled
                        binding.selectCategory.hint = "Amoled"

                    }
                    "Anime" -> {
                        //set hint Anime
                        binding.selectCategory.hint = "Anime"


                    }
                    "Exclusive" -> {
                        //set hint Exclusive
                        binding.selectCategory.hint = "Exclusive"


                    }
                    "Depth Effect" -> {
                        //set hint Depth Effect
                        binding.selectCategory.hint = "Depth Effect"


                    }
                    "Games" -> {
                        //set hint Games
                        binding.selectCategory.hint = "Games"


                    }
                    "Gradient" -> {
                        //set hint Gradient
                        binding.selectCategory.hint = "Gradient"


                    }
                    "Minimal" -> {
                        //set hint Minimal
                        binding.selectCategory.hint = "Minimal"


                    }
                    "Nature" -> {
                        //set hint Nature
                        binding.selectCategory.hint = "Nature"


                    }
                    "Shapes" -> {
                        //set hint Shapes
                        binding.selectCategory.hint = "Shapes"

                    }
                    "Sports" -> {
                        //set hint Sports
                        binding.selectCategory.hint = "Sports"


                    }
                    "Stocks" -> {
                        //set hint Stocks
                        binding.selectCategory.hint = "Stocks"


                    }
                    "Superheroes" -> {
                        //set hint Superheroes
                        binding.selectCategory.hint = "Superheroes"


                    }


                }

            }
            .show()
    }

    private fun uploadImage() {

        when (binding.selectCategory.hint) {
            "Abstract" -> {
                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Abstract/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val abstractMap = ImageList(fileName, it.toString())

                        firestore.collection("Abstract").document(fileName).set(abstractMap)

                        //upload firestore
                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()

                    }


                }


            }
            "Amoled" -> {
                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Amoled/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val amoledMap = ImageList(fileName, it.toString())

                        firestore.collection("Amoled").document(fileName).set(amoledMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()

                    }


                }
            }
            "Anime" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference = FirebaseStorage.getInstance().getReference("Anime/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val animeMap = ImageList(fileName, it.toString())

                        firestore.collection("Anime").document(fileName).set(animeMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }

            }
            "Exclusive" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Exclusive/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val exclusiveMap = ImageList(fileName, it.toString())

                        firestore.collection("Exclusive").document(fileName).set(exclusiveMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }
            }
            "Depth Effect" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Depth Effect/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val depthEffectMap = ImageList(fileName, it.toString())

                        firestore.collection("Depth Effect").document(fileName).set(depthEffectMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }

            }
            "Games" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference = FirebaseStorage.getInstance().getReference("Games/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val gamesMap = ImageList(fileName, it.toString())

                        firestore.collection("Games").document(fileName).set(gamesMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()

                    }

                }
            }
            "Gradient" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Gradient/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val gradientMap = ImageList(fileName, it.toString())

                        firestore.collection("Gradient").document(fileName).set(gradientMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }

            }
            "Minimal" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Minimal/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val minimalMap = ImageList(fileName, it.toString())

                        firestore.collection("Minimal").document(fileName).set(minimalMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }

            }
            "Nature" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Nature/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val natureMap = ImageList(fileName, it.toString())

                        firestore.collection("Nature").document(fileName).set(natureMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }

            }
            "Shapes" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Shapes/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val shapesMap = ImageList(fileName, it.toString())

                        firestore.collection("Shapes").document(fileName).set(shapesMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()

                    }


                }

            }
            "Shows" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference = FirebaseStorage.getInstance().getReference("Shows/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val showsMap = ImageList(fileName, it.toString())

                        firestore.collection("Shows").document(fileName).set(showsMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }

            }
            "Sports" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Sports/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val sportsMap = ImageList(fileName, it.toString())

                        firestore.collection("Sports").document(fileName).set(sportsMap)

                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()

                    }
                }
            }
            "Stocks" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Stocks/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val stocksMap = ImageList(fileName, it.toString())

                        firestore.collection("Stocks").document(fileName).set(stocksMap)


                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }

            }
            "Superheroes" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Uploading Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                //file name set current date
                val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
                val now = Date()
                val fileName = formatter.format(now)
                val storageReference =
                    FirebaseStorage.getInstance().getReference("Superheroes/$fileName")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val superHeroesMap = ImageList(fileName, it.toString())

                        firestore.collection("Superheroes").document(fileName).set(superHeroesMap)
                        //upload firestore
                        val recentUploadedMap = ImageList(fileName, it.toString())

                        firestore.collection("recentuploaded").document(fileName)
                            .set(recentUploadedMap)
                        binding.selectImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()
                    }


                }
            }
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Dexter.withContext(requireContext).withPermissions(
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
        )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    if (requestCode == pickImage && resultCode == Activity.RESULT_OK) {
                        if (data != null) {
                            imageUri = data.data
                            binding.selectImage.setImageURI(imageUri)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    list: List<PermissionRequest>,
                    permissionToken: PermissionToken
                ) {
                    permissionToken.continuePermissionRequest()
                }
            }).check()
    }

}