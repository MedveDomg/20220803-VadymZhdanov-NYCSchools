package com.medvedomg.a20220803_vadymzhdanov_nycschools.data

import android.content.Context
import com.github.doyaaaaaken.kotlincsv.client.CsvReader

interface SchoolRepository {

    suspend fun getSchoolList(): SchoolListResponse

    suspend fun getSatScoreList(): SatScoreListResponse
}

class SchoolListRepositoryImpl(
    val context: Context,
    val csvReader: CsvReader
) : SchoolRepository {

    override suspend fun getSchoolList(): SchoolListResponse {
        val pureList = csvReader.readAll(context.assets.open("2017_DOE_High_School_Directory.csv"))
        // skip first line with column names
        val list = pureList.subList(1, pureList.size).map {
            SchoolResponse(
                dbn = it[0],
                name = it[1],
                overview = it[3],
                location = it[18]
            )
        }
        return SchoolListResponse(list)
    }

    override suspend fun getSatScoreList(): SatScoreListResponse {
        val pureList = csvReader.readAll(context.assets.open("2012_SAT_Results.csv"))
        // skip first line with column names
        val list = pureList.subList(1, pureList.size).map {
            SatScoreResponse(
                dbn = it[0],
                scoreReading = it[3],
                scoreMath = it[4],
                scoreWriting = it[5]
            )
        }
        return SatScoreListResponse(list)
    }
}