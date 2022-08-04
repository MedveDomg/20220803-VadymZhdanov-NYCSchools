package com.medvedomg.a20220803_vadymzhdanov_nycschools.domain

import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.schoollist.SchoolModel
import com.medvedomg.a20220803_vadymzhdanov_nycschools.domain.util.Result
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.schoollist.SatScoreModel

class SchoolListInteractor(
    private val getSchoolListUseCase: GetSchoolListUseCase,
    private val getSatScoreListUseCase: GetSatScoreListUseCase
) {

    suspend fun getSchoolList(): Result<List<SchoolModel>> {
        val schoolListResult = getSchoolListUseCase(Unit)
        val satScoreListResult = getSatScoreListUseCase(Unit)
        return when {
            schoolListResult is Result.Success && satScoreListResult is Result.Success -> {
                val schoolList = schoolListResult.data.schoolList
                val satScoreList = satScoreListResult.data.satScoreList
                val models = schoolList.map { school ->
                    val satScoreModel = satScoreList.find { school.dbn == it.dbn }
                    SchoolModel(
                        dbn = school.dbn,
                        name = school.name,
                        overview = school.overview,
                        location = school.location,
                        satScoreModel = SatScoreModel(
                            scoreReading = satScoreModel?.scoreReading ?: "N/A",
                            scoreMath = satScoreModel?.scoreMath ?: "N/A",
                            scoreWriting = satScoreModel?.scoreWriting ?: "N/A"
                        )
                    )
                }
                Result.Success(models)
            }
            schoolListResult is Result.Error && satScoreListResult is Result.Error -> {
                Result.Error(Throwable("Data is not valid"))
            }
            else -> Result.Error(Throwable("Oops"))
        }
    }
}