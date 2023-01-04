package com.example.back4appshop.activities

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.back4appshop.Api
import com.example.back4appshop.R
import com.example.back4appshop.RetrofitClient
import com.example.back4appshop.models.OrderDataKeeper
import com.example.back4appshop.models.SendLittleOne
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textFirst: TextView = view.findViewById(R.id.first_text)
    var textsecond: TextView = view.findViewById(R.id.second_text)
}

var numberOfOrder =4
class SendAdapter(private var itemList: List<OrderDataKeeper>): RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.send_keeper, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("numberOfOrder", "numberOfOrder $numberOfOrder")

        val itemTest =  "EF0ewfQqeu"
        val service: Api = RetrofitClient.instance
        val call: Call<SendLittleOne> = service.getOrder("{\"userId\":\"$itemTest\"}")

        call.enqueue(object : Callback<SendLittleOne> {
            override fun onResponse(call: Call<SendLittleOne>, response: Response<SendLittleOne>) {
                itemList=response.body()!!.results
                Log.d("onResponse", "onResponse Car ${response.body()}")
                if (position >= 0 && position < itemList.size) {
                    holder.textFirst.text = itemList[position].productId
                    holder.textsecond.text = itemList[position].userId
                }
            }

            override fun onFailure(call: Call<SendLittleOne>, t: Throwable) {
                Log.d("onFailure", "onFailure $t")
            }

        })


    }

    override fun getItemCount(): Int {
        Log.d("numberOfOrder", "numberOfOrder $numberOfOrder")

        return numberOfOrder
    }
}