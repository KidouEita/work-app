package com.example.workapplication.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workapplication.R
import com.example.workapplication.api.model.News

class NewsRecyclerAdapter : RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    private var news = listOf<News>()
    private var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (news.isNotEmpty()) holder.bind(news[position])
    }

    override fun getItemCount() = news.size

    fun setNews(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }

    fun setItemClick(block: ((String) -> Unit)) {
        onItemClick = block
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textMsg = itemView.findViewById<TextView>(R.id.textMsg)
        private val textContent = itemView.findViewById<TextView>(R.id.textContent)
        private val textUpdatedTime = itemView.findViewById<TextView>(R.id.textUpdatedTime)
        private val moreLabel = itemView.findViewById<TextView>(R.id.moreLabel)

        fun bind(cellData: News) {
            with(cellData) {
                textMsg.text = chtMessage
                textContent.text = content
                textUpdatedTime.text = updateTime
                moreLabel.visibility = if (url.isNullOrBlank()) View.GONE else {
                    itemView.setOnClickListener {
                        onItemClick?.invoke(url)
                    }
                    View.VISIBLE
                }
            }
        }

    }
}
