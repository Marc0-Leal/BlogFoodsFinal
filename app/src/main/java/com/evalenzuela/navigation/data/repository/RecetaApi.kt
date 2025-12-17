package com.evalenzuela.navigation.data.repository

import com.evalenzuela.navigation.data.model.Receta
import retrofit2.Response
import retrofit2.http.*

interface RecetaApi {
    @GET("Receta")  // ⚠️ Endpoint
    suspend fun getAllRecetas(): Response<List<Receta>>

    @POST("Receta")
    suspend fun createReceta(@Body receta: Receta): Response<Receta>

    @DELETE("Receta/{id}")
    suspend fun deleteReceta(@Path("id") id: Int): Response<Unit>
}