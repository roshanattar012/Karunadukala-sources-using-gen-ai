package com.karunada.kala.domain.usecase

import com.karunada.kala.data.repository.HeritageRepository
import javax.inject.Inject

class RefreshCulturalDataUseCase @Inject constructor(
    private val repository: HeritageRepository
) {
    suspend operator fun invoke() {
        repository.refreshAllData()
    }
}
