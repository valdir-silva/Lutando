package com.alunando.lutando.presentation.screens.athlete_checkins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alunando.lutando.domain.model.Checkin
import com.alunando.lutando.domain.usecase.GetCheckinsByAthleteUseCase
import com.alunando.lutando.domain.usecase.GetCurrentUserUseCase
import com.alunando.lutando.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AthleteCheckinsViewModel(
    private val getCheckinsByAthleteUseCase: GetCheckinsByAthleteUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _checkins = MutableStateFlow<Resource<List<Checkin>>>(Resource.Loading())
    val checkins: StateFlow<Resource<List<Checkin>>> = _checkins.asStateFlow()

    init {
        loadAthleteCheckins()
    }

    private fun loadAthleteCheckins() {
        viewModelScope.launch {
            val currentUserResource = getCurrentUserUseCase()
            if (currentUserResource is Resource.Success<*>) {
                val athleteId = (currentUserResource.data as? com.google.firebase.auth.FirebaseUser)?.uid
                if (athleteId != null) {
                    getCheckinsByAthleteUseCase(athleteId).collectLatest {
                        _checkins.value = it
                    }
                } else {
                    _checkins.value = Resource.Error<List<Checkin>>("ID do atleta não encontrado.")
                }
            } else if (currentUserResource is Resource.Error<*>) {
                _checkins.value = Resource.Error<List<Checkin>>(currentUserResource.message ?: "Erro ao obter usuário atual.")
            }
        }
    }
}
