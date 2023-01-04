package com.example.back4appshop.adapters


import android.content.Intent
import com.example.back4appshop.models.CategoriesResults
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.back4appshop.Api
import com.example.back4appshop.R
import com.example.back4appshop.RetrofitClient
import com.example.back4appshop.activities.MainActivity
import com.example.back4appshop.models.JsonCategories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class CategoryAdapter(private var itemList: List<CategoriesResults>,
                        private val listenerClick: OnItemClickListener,
                      private val listener: OnDownloadCompleted
)

    : RecyclerView.Adapter<CategoryAdapter.ImageHolder>(){
    interface OnDownloadCompleted{
        fun onDownloadCompleted(data: String, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.lnear_category,
            parent,
            false
        )
        return ImageHolder(view)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {

        val service: Api = RetrofitClient.instance
        val call: Call<JsonCategories> = service.getCatApi()

        call.enqueue(object : Callback<JsonCategories> {

            override fun onResponse(
                call: Call<JsonCategories>,
                response: Response<JsonCategories>
            ) {
                itemList = response.body()!!.results
                holder.title.text = itemList[position].title
                Log.d("slm", "slm itemList -> $itemList")
            }

            override fun onFailure(call: Call<JsonCategories>, t: Throwable) {
                Log.d("haroomi", "haroomi $t")
            }
        })

        val myImageList = intArrayOf(R.drawable.bag, R.drawable.shoes, R.drawable.trousers, R.drawable.dress)
        holder.imageBack.setImageResource(myImageList[position])

    }

    override fun getItemCount(): Int {
        return 4
    }


    inner class ImageHolder(view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener{
        var imageBack: ImageView = view.findViewById(R.id.imageViewCategories)
        var title: TextView = view.findViewById(R.id.textViewCategories)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION) {
                listenerClick.onItemClick(position, v)

                Log.d("slm", "listener itemList -> ${itemList[position].objectId}")
                listener.onDownloadCompleted(itemList[position].objectId, position)

            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, v: View?)
    }
    fun getPhoto(position: Int): CategoriesResults? {
        return if (itemList.isNotEmpty()) itemList[position] else null
    }


}
