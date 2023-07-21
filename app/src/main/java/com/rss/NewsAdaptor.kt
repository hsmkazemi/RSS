package com.rss

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class NewsAdaptor(var newsList: MutableList<News>, var context: Context) :
    RecyclerView.Adapter<NewsAdaptor.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val description: TextView
        val author: TextView
        val image: ImageView

        init {
            view.apply {
                title = findViewById(R.id.titleNews)
                description = findViewById(R.id.description)
                author = findViewById(R.id.author)
                image = findViewById(R.id.imageView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scheme, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = newsList[position].title
            description.text = newsList[position].description
            author.text =
                newsList[position].author + " [" + newsList[position].publishedAt.dropLast(10) + "]"
            Glide.with(context).load(newsList[position].urlToImage).into(image)
        }
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scheme, parent, false)
//        return ViewHolder(view)
//    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.title.text
//    }
//    override fun getItemCount(): Int {
//        return newsList.size
//    }


}