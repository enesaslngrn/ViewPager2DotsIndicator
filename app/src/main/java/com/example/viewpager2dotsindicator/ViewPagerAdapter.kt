package com.example.viewpager2dotsindicator

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2dotsindicator.databinding.ViewpagerRowBinding

class ViewPagerAdapter(val images : List<Int>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(val binding: ViewpagerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.ViewPagerViewHolder {
        val binding = ViewpagerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewPagerViewHolder, position: Int) {
        val currentImage = images[position]
        holder.binding.ivViewPager.setImageResource(currentImage)

        holder.itemView.setOnClickListener {
            Intent(holder.itemView.context,SecondActivity::class.java).apply {
                holder.itemView.context.startActivity(this)
            }
        }
    }
}