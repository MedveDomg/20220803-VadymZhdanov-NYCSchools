package com.medvedomg.a20220803_vadymzhdanov_nycschools.domain

import com.medvedomg.a20220803_vadymzhdanov_nycschools.data.SchoolRepository
import com.medvedomg.a20220803_vadymzhdanov_nycschools.data.SchoolListResponse
import com.medvedomg.a20220803_vadymzhdanov_nycschools.domain.util.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetSchoolListUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    val schoolListRepository: SchoolRepository
) : CoroutineUseCase<Unit, SchoolListResponse>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): SchoolListResponse {
        return schoolListRepository.getSchoolList()
    }
}