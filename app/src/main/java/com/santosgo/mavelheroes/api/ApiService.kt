package com.santosgo.mavelheroes.api

import com.santosgo.mavelheroes.data.FullHero
import com.santosgo.mavelheroes.data.PowerstatsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //Declaración de funciones anotadas para acceder a la API.
    @GET("{id}/powerstats")
    suspend fun getHeroPowerstats(@Path("id") id : Int) : Response<PowerstatsResponse>

    @GET("{id}")
    suspend fun getFullHero(@Path("id") id : Int) : Response<FullHero>


}