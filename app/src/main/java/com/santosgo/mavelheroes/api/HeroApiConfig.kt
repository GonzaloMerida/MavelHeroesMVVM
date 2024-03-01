package com.santosgo.mavelheroes.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroApiConfig {
    companion object {

        //mi access_token = 10159208673232717
        const val USER_TOKEN = "ESCRIBE_TU_ACCESS_TOKEN"
        const val BASE_URL = "https://superheroapi.com/api/$USER_TOKEN/"

        //Definición de la api de Retrofit2.
        fun provideRetrofit() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}