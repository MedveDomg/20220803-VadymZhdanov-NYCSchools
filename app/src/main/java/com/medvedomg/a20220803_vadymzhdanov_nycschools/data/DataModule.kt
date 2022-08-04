package com.medvedomg.a20220803_vadymzhdanov_nycschools.data

import com.github.doyaaaaaken.kotlincsv.client.CsvReader
import org.koin.dsl.module

internal val dataModule = module {

    single { CsvReader() }

    single<SchoolRepository> {
        SchoolListRepositoryImpl(
            context = get(),
            csvReader = get()
        )
    }
}