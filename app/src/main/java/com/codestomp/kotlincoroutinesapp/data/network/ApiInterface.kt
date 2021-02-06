package com.codestomp.kotlincoroutinesapp.data.network

import com.codestomp.kotlincoroutinesapp.data.models.RecipesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {



    @GET("view-all-recipes.php?type=Top")
    fun getRecipesCall(): Call<RecipesResponse>

    @GET("view-all-recipes.php?type=Top")
    suspend fun getRecipes(): Response<RecipesResponse>

    companion object {
        operator fun invoke(): ApiInterface {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://codestomp.com/CodeStompApps/test/recipes/")
                .build()
                .create(ApiInterface::class.java)
        }
    }

}