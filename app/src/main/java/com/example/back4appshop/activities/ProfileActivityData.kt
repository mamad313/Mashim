package com.example.back4appshop.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.back4appshop.Api
import com.example.back4appshop.R
import com.example.back4appshop.RetrofitClient
import com.example.back4appshop.helper.Setting
import com.example.back4appshop.models.ResponseOrders
import com.example.back4appshop.models.ResultsProfileData
import com.example.back4appshop.models.ResultsResult
import kotlinx.android.synthetic.main.activity_profile_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


class ProfileActivityData : AppCompatActivity() {
    val REQ_CAMERA_PERM = 1001
    val REQ_CODE_TAKE_PHOTO = 1005
    val REQ_CODE_GALLERY = 1006

    lateinit var progressBarVar: ProgressBar
//    val txtPhone: EditText = findViewById(R.id.txtPhone)
//    val txtAddress: EditText = findViewById(R.id.txtAddress)
    lateinit var bitmapArray: ByteArray
    lateinit var imgProfile: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_data)

        imgProfile = findViewById(R.id.imgProfile)
        progressBarVar = findViewById(R.id.progressBar)

        btnPhoto.setOnClickListener {
            ActivityCompat.requestPermissions(
                this@ProfileActivityData,
                arrayOf(android.Manifest.permission.CAMERA),
                REQ_CAMERA_PERM
            )
        }
        btnPickGallery.setOnClickListener {
            val intent:Intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent,"Select image"), REQ_CODE_GALLERY)
        }
        val service: Api = RetrofitClient.instance
        val call: Call<ResultsResult> = service.getProfileGet("{\"userId\":\"${Setting(this@ProfileActivityData).setLoginInfo().toString()}\"}")
        call.enqueue(
            object : Callback<ResultsResult> {
                override fun onFailure(call: Call<ResultsResult>, t: Throwable) {
                    Log.d("onFailure", "onFailure")
                }
                override fun onResponse(call: Call<ResultsResult>, response: Response<ResultsResult>) {
                        val list = response.body()!!.results
//                    if(list?.adress?.length!! > 0){
                        val user: ResultsProfileData = ResultsProfileData(list.toString())
                        txtAddress.setText(list.last().adress)
                        txtPhone.setText(list.last().phone)

                        val picArr : ByteArray? = list.last().pic
                        val btm:Bitmap? = picArr?.let {
                            BitmapFactory.decodeByteArray(picArr,0,
                                it.size)
                        }
                        imgProfile.setImageBitmap(btm)
//                    }
                    Log.d("onResponse", "onResponse Profile-> $response ")
                    Log.d("onResponse", "onResponse Profile-> $list ")
                }
            }
        )
        btnSave.setOnClickListener {

            val userProfileData: ResultsProfileData = ResultsProfileData()
            userProfileData.adress=(txtAddress.text.toString())
            userProfileData.phone=(txtPhone.text.toString())
            userProfileData.pic= bitmapArray
            userProfileData.userId= Setting(this@ProfileActivityData).setLoginInfo().toString()

            val call: Call<ResponseOrders> = service.getProfile(userProfileData)
            call.enqueue(
                object : Callback<ResponseOrders> {
                    override fun onFailure(call: Call<ResponseOrders>, t: Throwable) {
                        Log.d("onFailure", "onFailure")
                    }
                    override fun onResponse(call: Call<ResponseOrders>, response: Response<ResponseOrders>) {
                        Toast.makeText(this@ProfileActivityData, getString(R.string.profileSaved),Toast.LENGTH_LONG).show()
                        Log.d("onResponse", "onResponse ")
                    }
                }
            )
        }

    }

    fun takePhoto(){
        val intentCamera: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intentCamera.resolveActivity(packageManager)!=null)
            startActivityForResult(intentCamera, REQ_CODE_TAKE_PHOTO)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePhoto()
        }
    }

    fun convertBmpToArray(bitmap: Bitmap){
        val stream: ByteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        bitmapArray = stream.toByteArray()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQ_CODE_TAKE_PHOTO && resultCode == RESULT_OK){
            val bitmap: Bitmap = data?.extras?.get("data") as Bitmap
            imgProfile.setImageBitmap(bitmap)

            convertBmpToArray(bitmap)
        }else if (requestCode == REQ_CODE_GALLERY && resultCode == RESULT_OK){
            val selectedImage: Uri? = data?.data
            var bitmap:Bitmap?=null
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)

            if(bitmap!=null) {
                convertBmpToArray(bitmap)
                imgProfile.setImageBitmap(bitmap)
            }
        }
    }
}