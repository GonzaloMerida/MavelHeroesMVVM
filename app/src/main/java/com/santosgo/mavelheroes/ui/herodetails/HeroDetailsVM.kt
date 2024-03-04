package com.santosgo.mavelheroes.ui.herodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.santosgo.mavelheroes.dependencies.MarvelHeroes
import com.santosgo.mavelheroes.repositories.HeroesRepository
import com.santosgo.mavelheroes.ui.herolist.HeroListUiState
import com.santosgo.mavelheroes.ui.herolist.HeroListVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HeroDetailsVM(
    private val heroesRepository: HeroesRepository
) : ViewModel() {

    private var _id: String = ""
    val id: String
        get() = _id

    private val _uiState: MutableStateFlow<HeroDetailsUiState> =
        MutableStateFlow(HeroDetailsUiState())
    val uiState: StateFlow<HeroDetailsUiState> = _uiState.asStateFlow()
    fun setId(id: String) {
        _id = id
    }

    fun setHero() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val heroResp = heroesRepository.getHero(id.toInt())
                if (heroResp.isSuccessful) {
                    val hero = heroResp.body()
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            hero = hero
                        )

                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = true
                        )
                    }
                }

            } catch (e: Error) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = true
                    )
                }

            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return HeroDetailsVM(
                    (application as MarvelHeroes).appContainer.heroesRepository
                ) as T
            }
        }
    }
}