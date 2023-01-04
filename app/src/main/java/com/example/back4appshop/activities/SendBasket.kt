package com.example.back4appshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.back4appshop.R
import kotlinx.android.synthetic.main.activity_send_basket.*


class SendBasket : AppCompatActivity() {
    private val introRecyclerAdapter = SendAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_basket)


        recycler_send.adapter= introRecyclerAdapter
        recycler_send.layoutManager = GridLayoutManager(this, 1)
    }
}