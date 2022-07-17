package com.example.retrofituse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adaptor:Myadaptor
    var totalResult=0
    var page=1
    private  val articalList= mutableListOf<Airtical>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNews()
//        recyclerView set up
        recyclerView=findViewById(R.id.recycleView)
        adaptor= Myadaptor(this@MainActivity,articalList)
        recyclerView.adapter=adaptor
        val layoutManager=LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager=layoutManager
        recyclerView.addOnScrollListener(object  :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (totalResult>layoutManager.itemCount && layoutManager.findFirstVisibleItemPosition()>=layoutManager.itemCount-7){
                    page++
                    getNews()
                }
            }
        })


    }

    private fun getNews() {
        val new = newServies.newsInstances.getHeadLIne("in", page)
        new.enqueue(object : Callback<news> {
            override fun onResponse(call: Call<news>, response: Response<news>) {
                val news = response.body()
                if (news != null) {

                    articalList.addAll(news.articles)
                    totalResult=news.totalResults
                    adaptor.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<news>, t: Throwable) {

            }
        })
    }
}