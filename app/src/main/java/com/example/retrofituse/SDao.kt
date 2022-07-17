package com.example.retrofituse

import android.graphics.pdf.PdfDocument
import com.google.gson.Gson
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2b5f5bd764ac4519aef01de0fdf64dd2
//https://newsapi.org/v2/everything?q=apple&from=2022-07-13&to=2022-07-13&sortBy=popularity&apiKey=2b5f5bd7
// 64ac4519aef01de0fdf64dd2
const val  BASE_URL="https://newsapi.org"
const val apiKey="2b5f5bd764ac4519aef01de0fdf64dd2"
interface SDao {
    @GET("/v2/top-headlines?apiKey=$apiKey")
    fun getHeadLIne(@Query("country")country:String, @Query("page")page:Int):Call<news>

}
object newServies{
    val newsInstances:SDao
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstances=retrofit.create(SDao::class.java)
    }
}