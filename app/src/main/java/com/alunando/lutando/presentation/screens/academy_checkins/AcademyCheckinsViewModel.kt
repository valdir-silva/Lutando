package com.alunando.lutando.presentation.screens.academy_checkins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alunando.lutando.domain.model.Checkin
import com.alunando.lutando.domain.usecase.GetAcademyByIdUseCase
import com.alunando.lutando.domain.usecase.GetCheckinsByAcademyUseCase
import com.alunando.lutando.domain.usecase.GetCurrentUserUseCase
import com.alunando.lutando.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AcademyCheckinsViewModel(
    private val getCheckinsByAcademyUseCase: GetCheckinsByAcademyUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getAcademyByIdUseCase: GetAcademyByIdUseCase
) : ViewModel() {

    private val _checkins = MutableStateFlow<Resource<List<Checkin>>>(Resource.Loading())
    val checkins: StateFlow<Resource<List<Checkin>>> = _checkins.asStateFlow()

    private val _academyId = MutableStateFlow<String?>(null)
    val academyId: StateFlow<String?> = _academyId.asStateFlow()

    init {
        loadAcademyCheckins()
    }

    private fun loadAcademyCheckins() {
        viewModelScope.launch {
            val currentUserResource = getCurrentUserUseCase()
            if (currentUserResource is Resource.Success<*>) {
                val userId = (currentUserResource.data as? com.google.firebase.auth.FirebaseUser)?.uid
                if (userId != null) {
                    // Assuming the current user is an academy owner and has an academy associated
                    // This part might need refinement based on how academy ownership is managed
                    val academy = getAcademyByIdUseCase(userId)
                    if (academy != null) {
                        val userAcademyId = academy.id
                        if (userAcademyId != null) {
                            _academyId.value = userAcademyId
                            getCheckinsByAcademyUseCase(userAcademyId).collectLatest {
                                _checkins.value = it
                            }
                        } else {
                            _checkins.value = Resource.Error<List<Checkin>>("Academia do usuário não encontrada.")
                        }
                    } else {
                        _checkins.value = Resource.Error<List<Checkin>>("Academia do usuário não encontrada.")
                    }
                } else {
                    _checkins.value = Resource.Error<List<Checkin>>("ID do usuário não encontrado.")
                }
            } else if (currentUserResource is Resource.Error<*>) {
                _checkins.value = Resource.Error<List<Checkin>>(currentUserResource.message ?: "Erro ao obter usuário atual.")
            }
        }
    }
}
