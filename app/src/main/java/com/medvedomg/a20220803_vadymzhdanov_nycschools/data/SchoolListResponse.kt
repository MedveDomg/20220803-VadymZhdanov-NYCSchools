package com.medvedomg.a20220803_vadymzhdanov_nycschools.data

data class SchoolListResponse(
    val schoolList: List<SchoolResponse>,
)

data class SchoolResponse(
    val dbn: String,
    val name: String,
    val overview: String,
    val location: String
)