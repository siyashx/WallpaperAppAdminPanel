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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.rangesun.a4kfreshwallpaperadmin.R
import com.rangesun.a4kfreshwallpaperadmin.databinding.FragmentChangeBinding
import com.rangesun.a4kfreshwallpaperadmin.model.ImageList
import java.util.*

@Suppress("DEPRECATION")
class ChangeFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentChangeBinding

    //pick image
    private val pickImage = 1

    //image uri
    private var imageUri: Uri? = null

    //available category list
    private val categoriesList = arrayOf(
        "Abstract", "Amoled", "Anime", "Exclusive", "Games",
        "Gradient", "Minimal", "Nature", "Shapes", "Shows", "Sports",
        "Stocks", "Superheroes"
    )

    //available catalog list
    private val catalogList =
        arrayOf("Spiderman", "Depth Effect", "Minimal (Catalog)", "Nature (Catalog)", "Space")

    //set image index
    private var selectedSetCategoryIndex: Int = 0


    //set image index
    private var selectedSetCatalogIndex: Int = 0

    //selected item
    private var selectedCategoriesItem = categoriesList[selectedSetCategoryIndex]

    //selected item
    private var selectedCatalogItem = catalogList[selectedSetCatalogIndex]

    //firestore
    private lateinit var firestore: FirebaseFirestore

    private lateinit var requireContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeBinding.inflate(layoutInflater, container, false)

        //text write disable for edit text
        binding.selectAvailableCategory.keyListener = null

        //initialize firestore
        firestore = FirebaseFirestore.getInstance()

        //text write disable for edit text
        binding.selectAvailableCategory.keyListener = null

        //radio button listener

        //category
        binding.categoryRadioBtn.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding.catalogRadioBtn.isChecked = false
                binding.selectAvailableCategory.hint = "Select Category..."
            }
        }

        //catalog
        binding.catalogRadioBtn.setOnCheckedChangeListener { _, b ->
            if (b) {
                binding.categoryRadioBtn.isChecked = false
                binding.selectAvailableCategory.hint = "Select Catalog..."
            }
        }

        //click select image
        binding.addOrChangeImage.setOnClickListener {
            //call method
            selectImage()
        }

        //click select category
        binding.selectAvailableCategory.setOnClickListener {
            //call method
            if (binding.categoryRadioBtn.isChecked) {
                selectCategory()
            } else if (binding.catalogRadioBtn.isChecked) {
                selectCatalog()
            }
        }


        //check empty
        binding.changeOrAddBtn.setOnClickListener {
            if (binding.categoryRadioBtn.isChecked && binding.selectAvailableCategory.hint.equals("Select Category...")) {
                Toast.makeText(
                    requireContext,
                    "Please select category!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else if (binding.catalogRadioBtn.isChecked && binding.selectAvailableCategory.hint.equals(
                    "Select Catalog..."
                )
            ) {
                Toast.makeText(
                    requireContext,
                    "Please select catalog!",
                    Toast.LENGTH_SHORT
                )
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
                selectedCategoriesItem = categoriesList[which]
            }
            .setPositiveButton("Apply") { _, _ ->
                when (selectedCategoriesItem) {
                    "Abstract" -> {
                        //set hint abstract
                        binding.selectAvailableCategory.hint = "Abstract"

                    }
                    "Amoled" -> {
                        //set hint amoled
                        binding.selectAvailableCategory.hint = "Amoled"

                    }
                    "Anime" -> {
                        //set hint Anime
                        binding.selectAvailableCategory.hint = "Anime"


                    }
                    "Exclusive" -> {
                        //set hint Exclusive
                        binding.selectAvailableCategory.hint = "Exclusive"


                    }
                    "Depth Effect" -> {
                        //set hint Depth Effect
                        binding.selectAvailableCategory.hint = "Depth Effect"


                    }
                    "Games" -> {
                        //set hint Games
                        binding.selectAvailableCategory.hint = "Games"


                    }
                    "Gradient" -> {
                        //set hint Gradient
                        binding.selectAvailableCategory.hint = "Gradient"


                    }
                    "Minimal" -> {
                        //set hint Minimal
                        binding.selectAvailableCategory.hint = "Minimal"


                    }
                    "Nature" -> {
                        //set hint Nature
                        binding.selectAvailableCategory.hint = "Nature"


                    }
                    "Shapes" -> {
                        //set hint Shapes
                        binding.selectAvailableCategory.hint = "Shapes"

                    }
                    "Sports" -> {
                        //set hint Sports
                        binding.selectAvailableCategory.hint = "Sports"


                    }
                    "Stocks" -> {
                        //set hint Stocks
                        binding.selectAvailableCategory.hint = "Stocks"


                    }
                    "Superheroes" -> {
                        //set hint Superheroes
                        binding.selectAvailableCategory.hint = "Superheroes"


                    }


                }

            }
            .show()
    }

    private fun selectCatalog() {
        MaterialAlertDialogBuilder(requireContext)
            .setTitle("Apply")
            .setSingleChoiceItems(catalogList, selectedSetCatalogIndex) { _, which ->
                selectedSetCatalogIndex = which
                selectedCatalogItem = catalogList[which]
            }
            .setPositiveButton("Apply") { _, _ ->
                when (selectedCatalogItem) {
                    "Spiderman" -> {
                        //set hint Spiderman
                        binding.selectAvailableCategory.hint = "Spiderman"

                    }
                    "Space" -> {
                        //set hint Space
                        binding.selectAvailableCategory.hint = "Space"

                    }
                    "Nature (Catalog)" -> {
                        //set hint Nature
                        binding.selectAvailableCategory.hint = "Nature (Catalog)"


                    }
                    "Minimal (Catalog)" -> {
                        //set hint Minimal
                        binding.selectAvailableCategory.hint = "Minimal (Catalog)"


                    }
                    "Depth Effect" -> {
                        //set hint Depth Effect
                        binding.selectAvailableCategory.hint = "Depth Effect"


                    }

                }

            }
            .show()
    }

    private fun uploadImage() {

        when (binding.selectAvailableCategory.hint) {
            "Abstract" -> {
                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/abstract.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val abstractCategoryMap = ImageList("Abstract", it.toString())

                        firestore.collection("Categories").document("Abstract")
                            .set(abstractCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/amoled.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val amoledCategoryMap = ImageList("Amoled", it.toString())

                        firestore.collection("Categories").document("Amoled")
                            .set(amoledCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/anime.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val animeCategoryMap = ImageList("Anime", it.toString())

                        firestore.collection("Categories").document("Anime")
                            .set(animeCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/exclusive.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val exclusiveCategoryMap = ImageList("Exclusive", it.toString())

                        firestore.collection("Categories").document("Exclusive")
                            .set(exclusiveCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Catalog Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/depth.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val depthCatalogMap = ImageList("Depth Effect", it.toString())

                        firestore.collection("Categories").document("Depth Effect")
                            .set(depthCatalogMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/games.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val gamesCategoryMap = ImageList("Games", it.toString())

                        firestore.collection("Categories").document("Games")
                            .set(gamesCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/gradient.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val gradientCategoryMap = ImageList("Gradient", it.toString())

                        firestore.collection("Categories").document("Gradient")
                            .set(gradientCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/minimal.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val minimalCategoryMap = ImageList("Minimal", it.toString())

                        firestore.collection("Categories").document("Minimal")
                            .set(minimalCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()

                    }


                }

            }
            "Minimal (Catalog)" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Changing Catalog Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                val storageReference =
                    FirebaseStorage.getInstance().getReference("Catalogs/minimal.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val minimalCatalogMap = ImageList("Minimal", it.toString())

                        firestore.collection("Catalogs").document("Minimal")
                            .set(minimalCatalogMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/nature.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val natureCategoryMap = ImageList("Nature", it.toString())

                        firestore.collection("Categories").document("Nature")
                            .set(natureCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()

                    }


                }

            }
            "Nature (Catalog)" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Changing Catalog Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Catalogs/nature.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val natureCatalogMap = ImageList("Nature", it.toString())

                        firestore.collection("Catalogs").document("Nature")
                            .set(natureCatalogMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/shapes.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val shapesCategoryMap = ImageList("Shapes", it.toString())

                        firestore.collection("Categories").document("Shapes")
                            .set(shapesCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/shows.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val showsCategoryMap = ImageList("Shows", it.toString())

                        firestore.collection("Categories").document("Shows")
                            .set(showsCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()

                    }


                }

            }

            "Spiderman" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Changing Catalog Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Catalogs/spiderman.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val spidermanCatalogMap = ImageList("Spiderman", it.toString())

                        firestore.collection("Catalogs").document("Spiderman")
                            .set(spidermanCatalogMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/sports.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val sportsCategoryMap = ImageList("Sports", it.toString())

                        firestore.collection("Categories").document("Sports")
                            .set(sportsCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
                        Toast.makeText(
                            requireContext,
                            "Successfully uploaded!",
                            Toast.LENGTH_SHORT
                        ).show()
                        if (progressDialog.isShowing) progressDialog.dismiss()

                    }


                }
            }
            "Space" -> {

                //show progress dialog
                val progressDialog = ProgressDialog(requireContext)
                progressDialog.setMessage("Changing Catalog Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()


                val storageReference =
                    FirebaseStorage.getInstance().getReference("Catalogs/space.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val spaceCatalogMap = ImageList("Space", it.toString())

                        firestore.collection("Catalogs").document("Space")
                            .set(spaceCatalogMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/stocks.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val stocksCategoryMap = ImageList("Stocks", it.toString())

                        firestore.collection("Categories").document("Stocks")
                            .set(stocksCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                progressDialog.setMessage("Changing Category Image...")
                progressDialog.setCancelable(false)
                progressDialog.show()

                val storageReference =
                    FirebaseStorage.getInstance().getReference("Categories/superheroes.png")

                //upload file
                storageReference.putFile(imageUri!!).addOnSuccessListener {

                    //get download url (firebase storage)
                    storageReference.downloadUrl.addOnSuccessListener {

                        //upload firestore
                        val superHeroesCategoryMap = ImageList("Superheroes", it.toString())

                        firestore.collection("Categories").document("Superheroes")
                            .set(superHeroesCategoryMap)

                        binding.addOrChangeImage.setImageResource(R.drawable.select_image)
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
                            binding.addOrChangeImage.setImageURI(imageUri)
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