package com.example.testtask.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.databinding.CategoryItemBinding
import com.example.testtask.domain.models.Category

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categories = mutableListOf<Category>()
    private var liveData = MutableLiveData<Int>()

    fun set(categories: List<Category>){
        this.categories = categories.toMutableList()
        notifyDataSetChanged()
    }

    fun getClickedLiveData(): LiveData<Int>{
        return liveData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater,parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        categories.getOrNull(position)?.let { category->
            holder.binding.categoryName.text = category.name
            holder.binding.categoryName.setOnClickListener {
                holder.setSelected()
                liveData.postValue(position)
            }

        }
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    class CategoryViewHolder(var binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root){

        @SuppressLint("ResourceAsColor")
        fun setSelected(){
            binding.categoryName.isSelected = true


        }

        @SuppressLint("ResourceAsColor")
        fun setUnselected(){
            binding.categoryName.isSelected = false
           // binding.categoryName.setTextColor(R.color.text)
            //binding.categoryName.backgroundTintList = ColorStateList.valueOf(R.color.white)
        }
    }
}