package com.arunkumar.newsupdates.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arunkumar.newsupdates.R
import com.arunkumar.newsupdates.models.NewsUpdateDomainModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_news_article.view.*

class NewsArticleAdapter : RecyclerView.Adapter<NewsArticleAdapter.NewsArticleViewHolder>() {

    private val articleList: ArrayList<NewsUpdateDomainModel> = ArrayList()

    fun setArticleNews(articleList: List<NewsUpdateDomainModel>) {
        this.articleList.clear()
        this.articleList.addAll(articleList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_news_article, parent, false)
        return NewsArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val article = articleList[position]
        Glide.with(holder.view)
            .load(article.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(holder.view.ivNewsImage)

        holder.view.tvNewsAuthor.text = article.author
        holder.view.tvNewsDescription.text = article.description


    }

    override fun getItemCount(): Int = articleList.size

    class NewsArticleViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
