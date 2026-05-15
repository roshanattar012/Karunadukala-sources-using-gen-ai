package com.karunada.kala.domain.usecase

import com.karunada.kala.data.ArtEvent
import com.karunada.kala.data.repository.HeritageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: HeritageRepository
) {
    operator fun invoke(): Flow<List<ArtEvent>> = repository.getLocalEvents()
}
