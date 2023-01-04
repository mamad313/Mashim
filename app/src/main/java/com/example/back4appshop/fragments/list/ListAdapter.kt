package com.example.back4appshop.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.back4appshop.Api
import com.example.back4appshop.model.User
import com.example.back4appshop.R
import com.example.back4appshop.RetrofitClient
import com.example.back4appshop.fragments.update.UpdateFragmentArgs
import com.example.back4appshop.models.CategoryResults
import com.example.back4appshop.models.JasonCategory
import com.example.back4appshop.viewmodel.UserViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListAdapter(private var photoList: List<CategoryResults>): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        /////////////////////////// Start
        val service: Api = RetrofitClient.instance
        val call: Call<JasonCategory> = service.getApi()

        call.enqueue(object : Callback<JasonCategory> {
            override fun onResponse(call: Call<JasonCategory>, response: Response<JasonCategory>) {

                photoList = response.body()!!.results



                if (position >= 0 && position < photoList.size) {
                    Picasso.get().load(currentItem.age)
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder).into(holder.itemView.imageView)

                    holder.itemView.number_text.text = currentItem.id.toString()
                    holder.itemView.firstName_text.text = photoList[position].title
                    holder.itemView.lastName_text.text = currentItem.lastName
                    holder.itemView.age_text.setText( String.format (currentItem.age.toString()))
                    Log.d("photoList", "photoList $photoList")

                }
            }

            override fun onFailure(call: Call<JasonCategory>, t: Throwable) {
                Log.d("onFailure", "onFailure $t")
            }

        })

        /////////////////// End



        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment3(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    fun setDataBasket(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

       return userList.size
    }



}