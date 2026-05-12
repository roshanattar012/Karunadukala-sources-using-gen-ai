package com.karunada.kala.domain.usecase

import com.karunada.kala.data.HeritageSite
import com.karunada.kala.data.repository.HeritageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHeritageSitesUseCase @Inject constructor(
    private val repository: HeritageRepository
) {
    operator fun invoke(): Flow<List<HeritageSite>> = repository.getLocalHeritageData()
}
