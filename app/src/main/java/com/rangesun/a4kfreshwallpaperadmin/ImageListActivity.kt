package com.rangesun.a4kfreshwallpaperadmin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.rangesun.a4kfreshwallpaperadmin.adapter.ImageListAdapter
import com.rangesun.a4kfreshwallpaperadmin.databinding.ActivityImageListBinding
import com.rangesun.a4kfreshwallpaperadmin.model.ImageList

@Suppress("DEPRECATION")
class ImageListActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityImageListBinding

    //firestore
    private lateinit var firestore: FirebaseFirestore

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firestore initialize
        firestore = FirebaseFirestore.getInstance()


        //get data
        val intent = intent
        val id = intent.getStringExtra("id")

        if (id.equals("Abstract")) {
            //set text abstract

            binding.listName.text = "Abstract"

            //firebase collection
            firestore.collection("Abstract").addSnapshotListener { value, _ ->
                val abstractList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                abstractList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                abstractList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, abstractList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, abstractList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Amoled")) {
            //set text amoled

            binding.listName.text = "Amoled"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Amoled").addSnapshotListener { value, _ ->
                val amoledList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                amoledList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                amoledList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, amoledList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, amoledList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }


            }

        } else if (id.equals("Anime")) {
            //set text anime

            binding.listName.text = "Anime"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Anime").addSnapshotListener { value, _ ->
                val animeList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                animeList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                animeList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, animeList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, animeList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Exclusive")) {
            //set text exclusive

            binding.listName.text = "Exclusive"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Exclusive").addSnapshotListener { value, _ ->
                val exclusiveList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                exclusiveList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                exclusiveList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, exclusiveList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, exclusiveList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Games")) {
            //set text games

            binding.listName.text = "Games"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Games").addSnapshotListener { value, _ ->
                val gamesList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                gamesList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                gamesList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, gamesList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, gamesList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Gradient")) {
            //set text gradient

            binding.listName.text = "Gradient"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Gradient").addSnapshotListener { value, _ ->
                val gradientList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                gradientList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                gradientList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, gradientList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, gradientList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Shapes")) {
            //set text shapes

            binding.listName.text = "Shapes"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Shapes").addSnapshotListener { value, _ ->
                val shapesList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                shapesList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                shapesList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, shapesList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, shapesList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Shows")) {
            //set text shows

            binding.listName.text = "Shows"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Shows").addSnapshotListener { value, _ ->
                val showsList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                showsList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                showsList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, showsList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, showsList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Sports")) {
            //set text sports

            binding.listName.text = "Sports"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Sports").addSnapshotListener { value, _ ->
                val sportsList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                sportsList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                sportsList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, sportsList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, sportsList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Stocks")) {
            //set text stocks

            binding.listName.text = "Stocks"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Stocks").addSnapshotListener { value, _ ->
                val stocksList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                stocksList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                stocksList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, stocksList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, stocksList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Spiderman") || id.equals("Superheroes")) {

            //set text superheroes
            binding.listName.text = "Superheroes"

            //link to recycler to superheroes

            //firebase collection
            firestore.collection("Superheroes").addSnapshotListener { value, _ ->
                val superheroesList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                superheroesList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                superheroesList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, superheroesList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, superheroesList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Depth Effect")) {

            //set text depth effect
            binding.listName.text = "Depth Effect"

            //link to recycler to depth

            //firebase collection
            firestore.collection("Depth Effect").addSnapshotListener { value, _ ->
                val depthList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                depthList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                depthList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, depthList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, depthList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Minimal")) {

            //set text minimal
            binding.listName.text = "Minimal"

            //link to recycler to minimal

            //firebase collection
            firestore.collection("Minimal").addSnapshotListener { value, _ ->
                val minimalList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                minimalList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                minimalList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, minimalList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = ImageListAdapter(this, minimalList)

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }

        } else if (id.equals("Nature") || id.equals("Space")) {

            //set text nature
            binding.listName.text = "Nature"

            //link to recycler to nature

            //firebase collection
            firestore.collection("Nature").addSnapshotListener { value, _ ->
                val natureList = ArrayList<ImageList>()
                val data = value?.toObjects(ImageList::class.java)
                natureList.addAll(data!!)

                //set has fixed true
                binding.imageListRecycler.setHasFixedSize(true)

                //set reverse for random list
                natureList.reverse()

                //linear layout manager set for recycler
                binding.imageListRecycler.layoutManager = GridLayoutManager(this, 3)

                //initialize adapter
                val imageListAdapter = ImageListAdapter(this, natureList)

                //set adapter for recycler
                binding.imageListRecycler.adapter = imageListAdapter

                //set pull to refresh
                binding.pullToRefreshImgList.setOnRefreshListener {

                    Handler().postDelayed({
                        binding.pullToRefreshImgList.isRefreshing = false
                        imageListAdapter.notifyDataSetChanged()
                    }, 3000)
                }
            }
        }

    }
}