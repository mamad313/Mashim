package com.example.back4appshop.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.back4appshop.R
import com.example.back4appshop.adapters.CategoryAdapter
import com.example.back4appshop.adapters.IntroSliderAdapter
import com.example.back4appshop.adapters.RecyclerAdapter
import com.example.back4appshop.models.JsonSlider
import com.example.back4appshop.models.SliderResults
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_slider.*
import kotlinx.android.synthetic.main.photo_details.*
import retrofit2.Response


class SliderDetailActivity() : AppCompatActivity(){
    private val introSliderAdapter = IntroSliderAdapter(ArrayList())
    val getPhoto = introSliderAdapter.getPhoto()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)



        Log.d("getPhoto2","getPhoto2-> $getPhoto")

        photo_author_slider.setText(getPhoto.title)
        photo_tags_slider.setText(getPhoto.createdAt)
        Picasso.get().load(getPhoto.data)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder).into(photo_image_Slider)


    }

}