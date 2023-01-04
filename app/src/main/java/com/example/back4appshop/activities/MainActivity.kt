package com.example.back4appshop.activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.back4appshop.HostActivity
import com.example.back4appshop.R
import com.example.back4appshop.adapters.IntroSliderAdapter
import com.example.back4appshop.adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerItemClickListener.OnRecyclerClickListener{

    lateinit var myViewPager2: ViewPager2

    private val introSliderAdapter = IntroSliderAdapter(ArrayList())
    private val introRecyclerAdapter = RecyclerAdapter(ArrayList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewPager2 = findViewById(R.id.introSliderViewPager)
        myViewPager2.adapter=introSliderAdapter
        


        recycler_view.adapter= introRecyclerAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.addOnItemTouchListener(RecyclerItemClickListener(this,recycler_view,this))

    }


    override fun onItemClick(view: View, position: Int) {


//        throw RuntimeException("Test Crash") // Force a crash



        val photo = introRecyclerAdapter.getPhoto(position)
        Log.d("bbbbbbbbb","bbbbbbb $photo")
        val intent = Intent(this,PhotoDetailsActivity::class.java)
        intent.putExtra(PHOTO_TRANSFER, photo)
        startActivity(intent)
        Toast.makeText(this, "kam feshar $position", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        Toast.makeText(this, "por feshar $position", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_button -> {
                startActivity(Intent(this, Categories::class.java))
                true
            }
            R.id.action_basket -> {
                startActivity(Intent(this, HostActivity::class.java))

                true
            }
            R.id.car -> {

                startActivity(Intent(this, SendBasket::class.java))

                true
            }
            R.id.profile -> {

                startActivity(Intent(this, ProfileActivityData::class.java))

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



}