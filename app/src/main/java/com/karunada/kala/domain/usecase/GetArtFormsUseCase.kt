package com.karunada.kala.domain.usecase

import com.karunada.kala.data.ArtForm
import com.karunada.kala.data.repository.HeritageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArtFormsUseCase @Inject constructor(
    private val repository: HeritageRepository
) {
    operator fun invoke(): Flow<List<ArtForm>> = repository.getLocalArtForms()
}
