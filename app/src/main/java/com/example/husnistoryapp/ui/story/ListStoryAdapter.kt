package com.example.husnistoryapp.ui.story

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.husnistoryapp.R
import com.example.husnistoryapp.databinding.ItemRowStoryBinding
import com.example.husnistoryapp.service.ListStoryItem
import com.example.husnistoryapp.ui.detail.DetailActivity
import com.example.husnistoryapp.ui.detail.DetailActivity.Companion.EXTRA_DATA

class ListStoryAdapter :
    PagingDataAdapter<ListStoryItem, ListStoryAdapter.ListViewHolder>(DIFF_ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(story)
        }
    }

    class ListViewHolder(private val binding: ItemRowStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: ListStoryItem) {
            binding.apply {
                tvName.text = story.name
                Glide.with(itemView.context)
                    .load(story.photo)
                    .fitCenter()
                    .apply(
                        RequestOptions
                            .placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    ).into(imgPhotos)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_DATA, story)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(imgPhotos, "story"),
                            Pair(tvName, "name"),
                            Pair(tvDescription, "desc")
                        )
                    itemView.context.startActivity(intent, optionsCompat.toBundle())
                }
            }
        }
    }

    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(
                oldStory: ListStoryItem,
                newStory: ListStoryItem
            ): Boolean {
                return oldStory == newStory
            }

            override fun areContentsTheSame(
                oldStory: ListStoryItem,
                newStory: ListStoryItem
            ): Boolean {
                return oldStory.name == newStory.name &&
                        oldStory.description == newStory.description &&
                        oldStory.photo == newStory.photo
            }
        }
    }
}