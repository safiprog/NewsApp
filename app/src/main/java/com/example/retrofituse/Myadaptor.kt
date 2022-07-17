package com.example.retrofituse

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Myadaptor(val context: Context,val articals:List<Airtical>): RecyclerView.Adapter<Myadaptor.MyViewModel>() {
    inner class MyViewModel(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView=itemView.findViewById<ImageView>(R.id.imageView)
        val titleText=itemView.findViewById<TextView>(R.id.titleText)
        val descriptionText=itemView.findViewById<TextView>(R.id.DesText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        val view=LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
        return MyViewModel(view)
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        val article=articals[position]
        holder.titleText.text=article.title
        holder.descriptionText.text=article.description
        Glide.with(context).load(article.urlToImage).into(holder.imageView)
        holder.itemView.setOnClickListener{
            val intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return  articals.size

    }
}

