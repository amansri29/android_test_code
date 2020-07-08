package com.golcha.testproject

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    //    Call<List<DataModal>> fetchData();
    @GET("list_data/")
    fun fetchData(): Call<List<DataModal?>?>?
}