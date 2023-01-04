package com.example.back4appshop.adapters



import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.back4appshop.Api
import com.example.back4appshop.R
import com.example.back4appshop.RetrofitClient
import com.example.back4appshop.activities.*
import com.example.back4appshop.models.CategoryResults
import com.example.back4appshop.models.JsonSlider
import com.example.back4appshop.models.SliderResults
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageViewHolderNew(view: View) : RecyclerView.ViewHolder(view) {
    var imageBack: ImageView = view.findViewById(R.id.imageSlideIcon)
}

var counter = 0
lateinit var photoListKeeper: List<SliderResults>


class IntroSliderAdapter( private var photoList: List<SliderResults>):
        RecyclerView.Adapter<ImageViewHolderNew>() {
    private val introRecyclerAdapter = RecyclerAdapter(ArrayList())




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolderNew {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container, parent, false)
        return ImageViewHolderNew(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolderNew, position: Int) {
        Log.d("onBindViewHolderSlider", "onBindViewHolderSlider ")

        holder.itemView.setOnClickListener{

            val context =holder.itemView.context
            val intent = Intent(context,SliderDetailActivity::class.java)
            context.startActivity(intent)
            counter = position

        }

        val service: Api = RetrofitClient.instance
        val call: Call<JsonSlider> = service.getSlider()

        call.enqueue(object : Callback<JsonSlider> {
            override fun onResponse(call: Call<JsonSlider>, response: Response<JsonSlider>) {

                photoList = response.body()!!.results
                photoListKeeper= photoList


                Log.d("photoListKeeper", "photoListKeeper -> $photoListKeeper")

                if (position >= 0 && position < photoList.size) {
                    Picasso.get().load(photoList[position].data)
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder).into(holder.imageBack)
                    Log.d("photoListSlider", "photoListSlider $photoList ")

                }

            }

            override fun onFailure(call: Call<JsonSlider>, t: Throwable) {
                Log.d("haroomi", "haroomi slider $t")
            }
        })
    }

    override fun getItemCount(): Int {
        return 3
    }

    fun getPhoto(): SliderResults {
        return photoListKeeper[counter]
    }

}