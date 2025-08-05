package com.alunando.lutando.data.repository

import com.alunando.lutando.domain.model.Checkin
import com.alunando.lutando.domain.repository.CheckinRepository
import com.alunando.lutando.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.snapshots

class CheckinRepositoryFirebaseImpl(
    private val firestore: FirebaseFirestore
) : CheckinRepository {

    override suspend fun addCheckin(checkin: Checkin): Resource<Unit> {
        return try {
            firestore.collection("checkins").add(checkin).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    override fun getCheckinsByAthlete(athleteId: String): Flow<Resource<List<Checkin>>> = flow {
        emit(Resource.Loading())
        try {
            firestore.collection("checkins")
                .whereEqualTo("athleteId", athleteId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .snapshots()
                .map { snapshot ->
                    val checkins = snapshot.toObjects(Checkin::class.java)
                    Resource.Success(checkins)
                }
                .collect { emit(it) }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    override fun getCheckinsByAcademy(academyId: String): Flow<Resource<List<Checkin>>> = flow {
        emit(Resource.Loading())
        try {
            firestore.collection("checkins")
                .whereEqualTo("academyId", academyId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .snapshots()
                .map { snapshot ->
                    val checkins = snapshot.toObjects(Checkin::class.java)
                    Resource.Success(checkins)
                }
                .collect { emit(it) }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}