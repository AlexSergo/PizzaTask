package com.example.testtask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.data.RepositoryImp
import com.example.testtask.domain.Repository

class ViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PizzaViewModel::class.java))
            return PizzaViewModel(repository) as T
        throw IllegalAccessException("ViewModel not found!")
    }
}