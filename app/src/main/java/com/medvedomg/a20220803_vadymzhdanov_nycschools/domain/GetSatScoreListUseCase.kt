package com.medvedomg.a20220803_vadymzhdanov_nycschools.domain

import com.medvedomg.a20220803_vadymzhdanov_nycschools.data.SatScoreListResponse
import com.medvedomg.a20220803_vadymzhdanov_nycschools.data.SchoolRepository
import com.medvedomg.a20220803_vadymzhdanov_nycschools.domain.util.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetSatScoreListUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    val schoolListRepository: SchoolRepository
) : CoroutineUseCase<Unit, SatScoreListResponse>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): SatScoreListResponse {
        return schoolListRepository.getSatScoreList()
    }
}