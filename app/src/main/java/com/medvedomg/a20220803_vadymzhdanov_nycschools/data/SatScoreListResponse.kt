package com.medvedomg.a20220803_vadymzhdanov_nycschools.data

data class SatScoreListResponse(val satScoreList: List<SatScoreResponse>)

data class SatScoreResponse(
    val dbn: String,
    val scoreReading: String,
    val scoreMath: String,
    val scoreWriting: String
)