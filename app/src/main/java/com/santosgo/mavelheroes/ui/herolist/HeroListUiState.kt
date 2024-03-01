package com.santosgo.mavelheroes.ui.herolist

import com.santosgo.mavelheroes.data.Hero

data class HeroListUiState(
    val isLoading : Boolean = true,
    val heroList: List<Hero> = emptyList(),
    val error : Boolean = false
)