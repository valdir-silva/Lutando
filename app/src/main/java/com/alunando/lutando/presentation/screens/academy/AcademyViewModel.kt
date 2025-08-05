package com.alunando.lutando.presentation.screens.academy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.usecase.AddAcademyUseCase
import com.alunando.lutando.domain.usecase.DeleteAcademyUseCase
import com.alunando.lutando.domain.usecase.GetAllAcademiesUseCase
import com.alunando.lutando.domain.usecase.GetAcademyByIdUseCase
import com.alunando.lutando.domain.usecase.UpdateAcademyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AcademyViewModel(
    private val addAcademyUseCase: AddAcademyUseCase,
    private val getAllAcademiesUseCase: GetAllAcademiesUseCase,
    private val getAcademyByIdUseCase: GetAcademyByIdUseCase,
    private val updateAcademyUseCase: UpdateAcademyUseCase,
    private val deleteAcademyUseCase: DeleteAcademyUseCase
) : ViewModel() {

    private val _academies = MutableStateFlow<List<Academy>>(emptyList())
    val academies: StateFlow<List<Academy>> = _academies.asStateFlow()

    private val _currentAcademy = MutableStateFlow<Academy?>(null)
    val currentAcademy: StateFlow<Academy?> = _currentAcademy.asStateFlow()

    init {
        loadAcademies()
    }

    fun loadAcademies() {
        viewModelScope.launch {
            getAllAcademiesUseCase().collect { academies ->
                _academies.value = academies
            }
        }
    }

    fun getAcademy(id: String) {
        viewModelScope.launch {
            _currentAcademy.value = getAcademyByIdUseCase(id)
        }
    }

    fun addAcademy(academy: Academy) {
        viewModelScope.launch {
            addAcademyUseCase(academy)
        }
    }

    fun updateAcademy(academy: Academy) {
        viewModelScope.launch {
            updateAcademyUseCase(academy)
        }
    }

    fun deleteAcademy(academy: Academy) {
        viewModelScope.launch {
            deleteAcademyUseCase(academy)
        }
    }

    fun resetCurrentAcademy() {
        _currentAcademy.value = null
    }
}