package com.alunando.lutando.presentation.screens.checkin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alunando.lutando.domain.model.Checkin
import com.alunando.lutando.domain.usecase.AddCheckinUseCase
import com.alunando.lutando.domain.usecase.GetCurrentUserUseCase
import com.alunando.lutando.util.Resource
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.usecase.GetAllAcademiesUseCase

class CheckinViewModel(
    private val addCheckinUseCase: AddCheckinUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getAllAcademiesUseCase: GetAllAcademiesUseCase
) : ViewModel() {

    private val _academyId = MutableStateFlow("")
    val academyId = _academyId.asStateFlow()

    private val _checkinEvent = MutableSharedFlow<CheckinEvent>()
    val checkinEvent = _checkinEvent.asSharedFlow()

    private val _academies = MutableStateFlow<Resource<List<Academy>>>(Resource.Loading<List<Academy>>())
    val academies: StateFlow<Resource<List<Academy>>> = _academies.asStateFlow()

    init {
        loadAcademies()
    }

    private fun loadAcademies() {
        viewModelScope.launch {
            getAllAcademiesUseCase().collectLatest {
                _academies.value = Resource.Success(it)
                Log.d("CheckinViewModel", "Academies: ${it.size} academies loaded. Status: Success")
            }
        }
    }

    fun onAcademyIdChange(newId: String) {
        _academyId.value = newId
    }

    fun onCheckinClick(academyId: String) {
        viewModelScope.launch {
            val currentUserResource = getCurrentUserUseCase()
            if (currentUserResource != null) {
                val athleteId = currentUserResource.uid

                if (academyId.isNotBlank()) {
                    val checkin = Checkin(
                        athleteId = athleteId,
                        academyId = academyId,
                        timestamp = Timestamp.now()
                    )
                    when (val result = addCheckinUseCase(checkin)) {
                        is Resource.Success -> {
                            Log.d("CheckinViewModel", "Check-in successful: ${result.message}")
                            _checkinEvent.emit(CheckinEvent.Success("Check-in realizado com sucesso!"))
                            // _academyId.value = "" // Clear academy ID after successful check-in
                        }
                        is Resource.Error -> {
                            Log.e("CheckinViewModel", "Check-in error: ${result.message}")
                            _checkinEvent.emit(CheckinEvent.Error(result.message ?: "Erro ao realizar check-in"))
                        }
                        is Resource.Loading -> {
                            Log.d("CheckinViewModel", "Check-in loading...")
                            // Handle loading state if needed
                        }
                    }
                } else {
                    _checkinEvent.emit(CheckinEvent.Error("ID do atleta ou da academia inválido."))
                }
            }
        }
    }

    sealed class CheckinEvent {
        data class Success(val message: String) : CheckinEvent()
        data class Error(val message: String) : CheckinEvent()
    }
}
