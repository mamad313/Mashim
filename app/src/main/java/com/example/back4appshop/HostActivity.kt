package com.example.back4appshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.back4appshop.model.User

import com.example.back4appshop.models.OrderDataKeeper
import com.example.back4appshop.models.ResponseOrders
import com.example.back4appshop.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_host.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HostActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)


//        val order1 = OrderDataKeeper(userId = "mamad", productId = "mamad product", count = "13")
//
//
//            val service: Api = RetrofitClient.instance
//            val call: Call<ResponseOrders> = service.saveOrder(order1)
//            call.enqueue(
//                    object : Callback<ResponseOrders> {
//                        override fun onFailure(call: Call<ResponseOrders>, t: Throwable) {
//                            Log.d("onFailure", "onFailure")
//                        }
//                        override fun onResponse(call: Call<ResponseOrders>, response: Response<ResponseOrders>) {
//                            val addedUser = response.body()
//                            Log.d("onResponse", "onResponse $addedUser")
//                        }
//                    }
//            )





    }


//    class DoOrder(): AsyncTask<Void,Void,Void>() {
//        override fun doInBackground(vararg params: Void?): Void? {
//            val orderVal : ResponseOrder = ResponseOrder("slm","slm","slm","slm","slm","slm")
//            val service: Api = RetrofitClient.instance
//            val call: Call<ResponseOrder> = service.saveOrder(orderVal)
//            call.enqueue(object : Callback<ResponseOrder> {
//                override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
//                    Log.d("onResponse", "onResponse")
//
//                }
//
//                override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
//                    Log.d("onFailure", "onFailure $t")
//                }
//
//            })
//            return null
//        }
//
//    }
}