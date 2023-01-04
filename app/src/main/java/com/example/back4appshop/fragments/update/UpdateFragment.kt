package com.example.back4appshop.fragments.update

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.back4appshop.Api
import com.example.back4appshop.R
import com.example.back4appshop.RetrofitClient
import com.example.back4appshop.helper.Setting
import com.example.back4appshop.model.User
import com.example.back4appshop.models.CategoryResults
import com.example.back4appshop.models.JasonCategory
import com.example.back4appshop.models.OrderDataKeeper
import com.example.back4appshop.models.ResponseOrders
import com.example.back4appshop.viewmodel.UserViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_update, container, false)

//                    view.updateFirstName.setText(args.currentUser.firstName)
//                    view.updateLastName.setText(args.currentUser.lastName)
//                    view.updateAge.setText(args.currentUser.age.toString())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        Picasso.get().load(args.currentUser.age)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder).into(view.imagePicasso)



        // Retrofit //

        val dataRetrofit = args.currentUser.firstName
        val service: Api = RetrofitClient.instance
        val call: Call<JasonCategory> = service.getApiId("{\"objectId\":\"$dataRetrofit\"}")
        call.enqueue(
            object : Callback<JasonCategory> {
                override fun onFailure(call: Call<JasonCategory>, t: Throwable) {
                    Log.d("onFailure", "onFailure")
                }
                override fun onResponse(call: Call<JasonCategory>, response: Response<JasonCategory>) {
                    val addedUser = response.body()!!.results
                    view.updateFirstName.setText(addedUser[0].title)
                    view.updateLastName.setText(args.currentUser.lastName)
                    val test = view.updateAge.text
                    view.updateAge.text = test
                    Log.d("onResponse", "onResponse $addedUser")
                }
            }
        )

        // Retrofit //

        view.saveOrder.setOnClickListener {
            val order1 = OrderDataKeeper(userId = Setting(view.context).setLoginInfo().toString(), productId = args.currentUser.firstName, count = view.updateAge.text.toString())


            val service: Api = RetrofitClient.instance
            val call: Call<ResponseOrders> = service.saveOrder(order1)
            call.enqueue(
                object : Callback<ResponseOrders> {
                    override fun onFailure(call: Call<ResponseOrders>, t: Throwable) {
                        Log.d("onFailure", "onFailure")
                    }
                    override fun onResponse(call: Call<ResponseOrders>, response: Response<ResponseOrders>) {
                        val addedUser = response.body()
                        Log.d("onResponse", "onResponse $addedUser")
                    }
                }
            )
        }
        view.UpdateButton.setOnClickListener {
            updateItem()
        }
        view.DeleteButton.setOnClickListener {
            deleteUser()
        }

        setHasOptionsMenu(true)


        return view
    }

    private fun updateItem() {
//        val firstName = updateFirstName.text.toString()
//        val lastName = updateLastName.text.toString()
//        val age = updateAge.text.toString()


        val updateUser = User(args.currentUser.id, args.currentUser.firstName, args.currentUser.lastName, args.currentUser.age)

        mUserViewModel.updateUser(updateUser)
        Toast.makeText(requireContext(), "Oke", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_updateFragment3_to_listFragment)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        mUserViewModel.deleteUser(args.currentUser)

    }

}