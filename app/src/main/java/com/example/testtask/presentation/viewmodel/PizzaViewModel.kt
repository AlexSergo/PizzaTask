package com.example.testtask.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.Repository
import com.example.testtask.domain.models.Pizza
import com.example.testtask.domain.usecases.GetPositionsFromLocalDataSourceUseCase
import com.example.testtask.domain.usecases.GetPositionsUseCase
import com.example.testtask.domain.usecases.SavePositionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PizzaViewModel(private val repository: Repository): ViewModel() {

    private val liveData = MutableLiveData<List<Pizza>>()
    private val getPositionsUseCase by lazy(LazyThreadSafetyMode.NONE){
        GetPositionsUseCase(repository)
    }
    private val savePositionsUseCase by lazy(LazyThreadSafetyMode.NONE){
        SavePositionsUseCase(repository)
    }
    private val getPositionsFromLocalDataSourceUseCase by lazy(LazyThreadSafetyMode.NONE){
        GetPositionsFromLocalDataSourceUseCase(repository)
    }

    fun getLiveData(): LiveData<List<Pizza>>{
        return liveData
    }

    fun getPositions() = viewModelScope.launch(Dispatchers.IO){
        liveData.postValue(getPositionsUseCase.execute())
    }

    fun savePositions(positions: List<Pizza>) = viewModelScope.launch(Dispatchers.IO){
        savePositionsUseCase.execute(positions)
    }

    fun getPositionsFromLocalDataSource() = viewModelScope.launch(Dispatchers.IO){
        liveData.postValue(getPositionsFromLocalDataSourceUseCase.execute())
    }
}