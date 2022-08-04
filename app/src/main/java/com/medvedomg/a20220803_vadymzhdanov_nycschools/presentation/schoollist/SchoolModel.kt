package com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.schoollist

import android.os.Parcelable
import java.io.Serializable

data class SchoolModel(
    val dbn: String,
    val name: String,
    val overview: String,
    val location: String,
    val satScoreModel: SatScoreModel
): Serializable

data class SatScoreModel(
    val scoreReading: String,
    val scoreMath: String,
    val scoreWriting: String
): Serializable