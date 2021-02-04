package com.codestomp.kotlincoroutinesapp.data.models

data class RecipesResponse(
    val `data`: ArrayList<Data>,
    val status: String
)