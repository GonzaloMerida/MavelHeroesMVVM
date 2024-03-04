package com.santosgo.mavelheroes.data

import java.io.StringBufferInputStream

//Clase con los objetos del RecyclerView.
data class Hero(
    val name : String,
    val power : Int,
    val intelligence : Int,
    val photo : String,
    val description : String,
    val id : String
)