package com.santosgo.mavelheroes.ui.herodetails

import com.santosgo.mavelheroes.data.Hero

data class HeroDetailsUiState(
    val isLoading : Boolean = true,
    val hero : Hero? = null,
    val error : Boolean = false
    )