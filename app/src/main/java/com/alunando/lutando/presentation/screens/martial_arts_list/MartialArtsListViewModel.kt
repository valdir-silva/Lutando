package com.alunando.lutando.presentation.screens.martial_arts_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alunando.lutando.domain.model.MartialArt
import com.alunando.lutando.domain.usecase.GetAllMartialArtsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MartialArtsListViewModel(
    private val getAllMartialArtsUseCase: GetAllMartialArtsUseCase
) : ViewModel() {

    private val _martialArts = MutableStateFlow<List<MartialArt>>(emptyList())
    val martialArts: StateFlow<List<MartialArt>> = _martialArts.asStateFlow()

    init {
        viewModelScope.launch {
            getAllMartialArtsUseCase().collect {
                _martialArts.value = it
            }
        }
    }
}
