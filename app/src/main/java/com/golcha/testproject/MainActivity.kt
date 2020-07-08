package com.golcha.testproject

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val BASE_URL = "http://192.168.40.113/"
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        val retrofitInterface = retrofit.create(RetrofitInterface::class.java)
        val call = retrofitInterface.fetchData()
        call!!.enqueue(object : Callback<List<DataModal?>?> {
            override fun onResponse(call: Call<List<DataModal?>?>, response: Response<List<DataModal?>?>) {
                for (dataModal in response.body()!!) {
                    Log.i(TAG, "onResponse: comments " + dataModal!!.comments)
                }
                val listAdapter = ListAdapter(response.body() as List<DataModal>)
                recyclerView!!.setHasFixedSize(true)
                recyclerView!!.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView!!.adapter = listAdapter
            }

            override fun onFailure(call: Call<List<DataModal?>?>, t: Throwable) {
                Log.e(TAG, "onFailure: " + "Error" + t.message)
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}