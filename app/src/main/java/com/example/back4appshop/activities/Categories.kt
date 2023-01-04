package com.example.back4appshop.activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.back4appshop.Api
import com.example.back4appshop.R
import com.example.back4appshop.RetrofitClient
import com.example.back4appshop.adapters.CategoryAdapter
import com.example.back4appshop.adapters.IntroSliderAdapter
import com.example.back4appshop.adapters.RecyclerAdapter
import com.example.back4appshop.models.CategoryResults
import com.example.back4appshop.models.JasonCategory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//lateinit var photoList: List<CategoryResults>

class Categories: AppCompatActivity(), CategoryAdapter.OnItemClickListener, CategoryAdapter.OnDownloadCompleted {

    private val flickerRecyclerViewAdapter= RecyclerAdapter(ArrayList())


//    private val introSliderAdapter = IntroSliderAdapter(ArrayList())


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


//        introSliderViewPager.adapter = introSliderAdapter
        recycler_view_category.adapter= CategoryAdapter(ArrayList(), this, this)
        recycler_view_category.layoutManager= LinearLayoutManager(this)

    }


    override fun onItemClick(position: Int, v: View?) {

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
//        var catItem: CategoriesResults = ArrayList()
//
//        val service: Api = RetrofitClient.instance
//        val call: Call<JasonCategory> = service.getApiId("{\"ID\":\"9NWtDLuuiQ\"}")
//        call.enqueue(object : Callback<JasonCategory> {
//            override fun onResponse(call: Call<JasonCategory>, response: Response<JasonCategory>) {
//
//                photoList = response.body()!!.results
//
//                Log.d("photoList", "photoList $photoList")
//                val imageBack: ImageView = v!!.findViewById(R.id.imageBack)
//                val title: TextView = v.findViewById(R.id.button)
//                if (position >= 0 && position < photoList.size) {
//                    Picasso.get().load(photoList[position].image)
//                            .error(R.drawable.placeholder)
//                            .placeholder(R.drawable.placeholder).into(imageBack)
//                    title.text = photoList[position].title
//                    Log.d("photoList", "photoList $photoList")
//
//                }
//            }
//
//            override fun onFailure(call: Call<JasonCategory>, t: Throwable) {
//                Log.d("onFailure", "onFailure $t")
//            }
//
//        })


//        Toast.makeText(this,"Item $position", Toast.LENGTH_SHORT).show()
//        val photo = introSliderAdapter.getPhoto(position)
//        Log.d("introSliderAdapter","introSliderAdapter $photo")



    }

    override fun onDownloadCompleted(data: String, position: Int) {
        var photoList: List<CategoryResults>
        val service: Api = RetrofitClient.instance
//        val call: Call<JasonCategory> = service.getApi()
        val call: Call<JasonCategory> = service.getApiId("{\"ID\":\"$data\"}")


        call.enqueue(object : Callback<JasonCategory> {
            override fun onResponse(call: Call<JasonCategory>, response: Response<JasonCategory>) {

                photoList = response.body()!!.results

                flickerRecyclerViewAdapter.loadNewData(photoList)
//
                Log.d("photoList", "photoList $photoList")


            }

            override fun onFailure(call: Call<JasonCategory>, t: Throwable) {
                Log.d("onFailure", "onFailure $t")
            }

        })
    }

}



