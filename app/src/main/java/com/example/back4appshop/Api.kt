package com.example.back4appshop



import com.example.back4appshop.models.*
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @Headers("X-Parse-REST-API-Key:QGCd2704zzJBAwcVXWrvAZV2XwcfeZgkLheHYtB1",
            "X-Parse-Application-Id:Vr8pNf10sCsasXrtnKTJAzQeg4nlpSiH8FR6M7R4",
            "Content-Type:application/json")
    @GET("category")
    fun getApi(): Call<JasonCategory>

    @Headers("X-Parse-REST-API-Key:QGCd2704zzJBAwcVXWrvAZV2XwcfeZgkLheHYtB1",
             "X-Parse-Application-Id:Vr8pNf10sCsasXrtnKTJAzQeg4nlpSiH8FR6M7R4",
            "Content-Type:application/json")
    @GET("categories")
    fun getCatApi(): Call<JsonCategories>

    @Headers("X-Parse-REST-API-Key:QGCd2704zzJBAwcVXWrvAZV2XwcfeZgkLheHYtB1",
            "X-Parse-Application-Id:Vr8pNf10sCsasXrtnKTJAzQeg4nlpSiH8FR6M7R4",
            "Content-Type:application/json")
    @GET("category")
    fun getApiId(@Query("where") where:String ): Call<JasonCategory>

    @Headers("X-Parse-REST-API-Key:QGCd2704zzJBAwcVXWrvAZV2XwcfeZgkLheHYtB1",
            "X-Parse-Application-Id:Vr8pNf10sCsasXrtnKTJAzQeg4nlpSiH8FR6M7R4",
            "Content-Type:application/json")
    @GET("slider")
    fun getSlider(): Call<JsonSlider>

    @Headers("X-Parse-REST-API-Key:QGCd2704zzJBAwcVXWrvAZV2XwcfeZgkLheHYtB1",
        "X-Parse-Application-Id:Vr8pNf10sCsasXrtnKTJAzQeg4nlpSiH8FR6M7R4",
        "Content-Type:application/json")

    @POST("Order")
    fun saveOrder(@Body orderData: OrderDataKeeper): Call<ResponseOrders>

    @Headers("X-Parse-REST-API-Key:QGCd2704zzJBAwcVXWrvAZV2XwcfeZgkLheHYtB1",
        "X-Parse-Application-Id:Vr8pNf10sCsasXrtnKTJAzQeg4nlpSiH8FR6M7R4",
        "Content-Type:application/json")

    @GET("Order")
    fun getOrder(@Query("where") where:String ): Call<SendLittleOne>

    @Headers("X-Parse-REST-API-Key:QGCd2704zzJBAwcVXWrvAZV2XwcfeZgkLheHYtB1",
        "X-Parse-Application-Id:Vr8pNf10sCsasXrtnKTJAzQeg4nlpSiH8FR6M7R4",
        "Content-Type:application/json")

    @POST("userProfile")
    fun getProfile(@Body baseProfileData: ResultsProfileData): Call<ResponseOrders>
    @Headers("X-Parse-REST-API-Key:QGCd2704zzJBAwcVXWrvAZV2XwcfeZgkLheHYtB1",
        "X-Parse-Application-Id:Vr8pNf10sCsasXrtnKTJAzQeg4nlpSiH8FR6M7R4",
        "Content-Type:application/json")

    @GET("userProfile")
    fun getProfileGet(@Query("where") where:String ): Call<ResultsResult>

}