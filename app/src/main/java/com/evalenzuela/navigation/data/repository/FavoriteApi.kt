package com.evalenzuela.navigation.data.repository

import com.evalenzuela.navigation.data.model.Favorite
import retrofit2.Response
import retrofit2.http.*

interface FavoriteApi {
    @GET("Favorito")
    suspend fun getAllFavorites(): Response<List<Favorite>>

    @POST("Favorito")
    suspend fun addFavorite(@Body favorite: Favorite): Response<Favorite>

    @DELETE("Favorito/{id}")
    suspend fun deleteFavorite(@Path("id") id: Int): Response<Unit>
}