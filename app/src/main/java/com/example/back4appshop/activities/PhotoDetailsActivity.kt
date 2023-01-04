package com.example.back4appshop.activities


import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.back4appshop.R
import com.example.back4appshop.helper.Setting
import com.example.back4appshop.model.User
import com.example.back4appshop.models.CategoryResults
import com.example.back4appshop.models.SliderResults
import com.example.back4appshop.viewmodel.UserViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.photo_details.*

class PhotoDetailsActivity() : BaseActivity() {
    private lateinit var mUserViewModel: UserViewModel

    lateinit var photo: CategoryResults

    override fun onCreate(savedInstanceState: Bundle?) {
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_details)

        photo= intent.extras?.getParcelable<CategoryResults>(PHOTO_TRANSFER) as CategoryResults

        photo_title.text = photo.title
        photo_tags.text = photo.updatedAt
        photo_author.text = photo.title


        buyIt.setOnClickListener {

            insertDataToDatabase()

        }

        Picasso.get().load(photo.image)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(photo_image)
    }

    private fun insertDataToDatabase() {
//        val firstName = firstName.text.toString()
//        val lastName = lastName.text.toString()

        val user = User (0, photo.objectId, Setting(this@PhotoDetailsActivity).setLoginInfo().toString(), photo.image)

        mUserViewModel.addUser(user)

//        Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()

//        findNavController().navigate(R.id.action_addFragment_to_listFragment)

    }

}