package com.example.cauroselapp

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class Adapter(val context: Context, val articlesList: List<Articles>):
    androidx.recyclerview.widget.RecyclerView.Adapter<Adapter.viewholder>() {

    inner class viewholder(itemView: View): ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.imageView)
        val text = itemView.findViewById<TextView>(R.id.titlleText)
        val text2 = itemView.findViewById<TextView>(R.id.descText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(context).inflate(R.layout.items,parent,false))
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val article = articlesList[position]
        Glide.with(context).load(article.urlToImage).into(holder.image)
        holder.text.text = article.title
        holder.text2.text = article.description
        holder.itemView.setOnClickListener {
            val intent = Intent(context,detailedVIew::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }

    }

}