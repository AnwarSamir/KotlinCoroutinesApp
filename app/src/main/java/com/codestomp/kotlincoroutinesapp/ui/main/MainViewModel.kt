package com.codestomp.kotlincoroutinesapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codestomp.kotlincoroutinesapp.data.models.RecipesResponse
import com.codestomp.kotlincoroutinesapp.data.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel: ViewModel()
{
    var recipes:MutableLiveData<RecipesResponse>?= MutableLiveData()


    init {
        viewModelScope.launch {
            val response=getRecipes()
            if (response.isSuccessful)
                recipes!!.postValue(response.body())

        }
    }

    suspend fun getRecipes(): Response<RecipesResponse> {

        return withContext(Dispatchers.IO) {
            ApiInterface().getRecipes()
        }

    }
}