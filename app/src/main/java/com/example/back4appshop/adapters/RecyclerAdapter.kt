package com.example.back4appshop.adapters

import com.example.back4appshop.models.CategoryResults
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.back4appshop.Api
import com.example.back4appshop.R
import com.squareup.picasso.Picasso
import com.example.back4appshop.RetrofitClient
import com.example.back4appshop.activities.Categories
import com.example.back4appshop.models.JasonCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var imageBack: ImageView = view.findViewById(R.id.imageBack)
    var title: TextView = view.findViewById(R.id.button)
}


var number = 1
var numberClothes = 6
lateinit var photoList2: List<CategoryResults>

class RecyclerAdapter(private var photoList: List<CategoryResults>): RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.brows, parent, false)
        return ImageViewHolder(view)
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val service: Api = RetrofitClient.instance
        val call: Call<JasonCategory> = service.getApi()
//        val catItem = "y8cN6Wmwjb"
//        val call: Call<JasonCategory> = service.getApiId("{\"ID\":\"$catItem\"}")


        call.enqueue(object : Callback<JasonCategory> {
            override fun onResponse(call: Call<JasonCategory>, response: Response<JasonCategory>) {

                if(number == 1) {
                    photoList = response.body()!!.results
                }else {
                photoList = photoList2
                }


                Log.d("photoList for One Time", "photoList for One Time $photoList")

                if (position >= 0 && position < photoList.size) {
                    Picasso.get().load(photoList[position].image)
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder).into(holder.imageBack)
                    holder.title.text = photoList[position].title
                    Log.d("photoList", "photoList $photoList")

                }
            }

            override fun onFailure(call: Call<JasonCategory>, t: Throwable) {
                Log.d("onFailure", "onFailure $t")
            }

        })
    }

    override fun getItemCount(): Int {
        return numberClothes
    }

    fun getPhoto(position: Int): CategoryResults? {
        return if (photoList.isNotEmpty()) photoList[position] else null
    }
    fun loadNewData(newPhotos: List<CategoryResults>) {
        photoList = newPhotos
        photoList2 =newPhotos
        numberClothes = photoList.size
        number = 2
        notifyDataSetChanged()
        Log.d("photoList for Two Time", "photoList for Two Time $photoList")
    }

//    fun filterByCat(catItem: CategoriesResults){
//
////        val service: Api = RetrofitClient.instance
////        val call: Call<JsonCategories> = service.getApiId("{\"ID\":\""+catItem.ID+"\"}")
////
////        call.enqueue(object : Callback<JsonCategories> {
////
////            override fun onResponse(call: Call<JsonCategories>, response: Response<JsonCategories>) {
////
////                val listItem: List<com.example.back4appshop.models.CategoriesResults> = response.body()!!.results
//
////                recycler_view.adapter = RecyclerAdapter(listItem)
//
////                Log.d("slm", "slm itemList -> ")
////            }
////
////            override fun onFailure(call: Call<JsonCategories>, t: Throwable) {
////                Log.d("haroomi", "haroomi $t")
////            }
////        })
////
//}

}
