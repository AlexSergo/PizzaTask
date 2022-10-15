package com.example.testtask.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.databinding.BannerItemBinding
import com.example.testtask.domain.models.Banner

class BannerAdapter() : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    private var banners = mutableListOf<Banner>()

    fun set(banners: List<Banner>){
        this.banners= banners.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BannerItemBinding.inflate(inflater,parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        banners.getOrNull(position)?.let { banner->
            holder.binding.image.setImageResource(banner.code)
        }
    }

    override fun getItemCount(): Int {
        return banners.count()
    }

    class BannerViewHolder(var binding: BannerItemBinding): RecyclerView.ViewHolder(binding.root)
}