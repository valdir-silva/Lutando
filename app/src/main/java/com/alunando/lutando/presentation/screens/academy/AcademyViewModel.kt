package com.alunando.lutando.presentation.screens.academy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.usecase.AddAcademyUseCase
import com.alunando.lutando.domain.usecase.DeleteAcademyUseCase
import com.alunando.lutando.domain.usecase.GetAllAcademiesUseCase
import com.alunando.lutando.domain.usecase.GetAcademyByIdUseCase
import com.alunando.lutando.domain.usecase.UpdateAcademyUseCase
import com.alunando.lutando.domain.usecase.UserAlreadyHasAcademyException
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

    private val _hasAcademy = MutableStateFlow(false)
    val hasAcademy: StateFlow<Boolean> = _hasAcademy.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadAcademies()
        checkIfUserHasAcademy()
    }

    private fun checkIfUserHasAcademy() {
        viewModelScope.launch {
            getAllAcademiesUseCase().collect { academies ->
                _hasAcademy.value = academies.isNotEmpty()
            }
        }
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
            try {
                addAcademyUseCase(academy)
                _errorMessage.value = null // Clear any previous error
            } catch (e: UserAlreadyHasAcademyException) {
                _errorMessage.value = e.message
            } catch (e: Exception) {
                _errorMessage.value = "An unexpected error occurred: ${e.message}"
            }
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

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}