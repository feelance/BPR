package com.freelance.bprfront.services.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freelance.bprfront.model.WeekRoutine
import com.freelance.bprfront.services.client.WeekRoutinesClient
import com.freelance.bprfront.services.config.RetrofitInstance
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeekRoutinesVM : ViewModel() {

    private val _routines= MutableLiveData<WeekRoutine>()
    val routines: LiveData<WeekRoutine> = _routines

    fun fetchAllRoutines() {
        viewModelScope.launch {

            val weekRoutines = async { WeekRoutinesClient.getAllRoutines()}
            val getWeekRoutines = weekRoutines.await()
            if(getWeekRoutines.isSuccessful){
                _routines.postValue(getWeekRoutines.body())
            }
        }

    }
}