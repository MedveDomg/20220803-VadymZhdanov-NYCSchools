package com.medvedomg.a20220803_vadymzhdanov_nycschools.domain

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DispatchersName {
    const val IO = "DispatcherIO"
    const val Main = "DispatcherMain"
    const val Immediate = "DispatcherImmediate"
}

internal val domainModule = module {

    single(named(DispatchersName.IO)) { Dispatchers.IO }

    single(named(DispatchersName.Main)) { Dispatchers.Main }

    single(named(DispatchersName.Immediate)) { Dispatchers.Main.immediate }

    factory {
        GetSatScoreListUseCase(
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO)),
            schoolListRepository = get()
        )
    }

    factory {
        GetSchoolListUseCase(
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO)),
            schoolListRepository = get()
        )
    }

    factory {
        SchoolListInteractor(
            getSchoolListUseCase = get(),
            getSatScoreListUseCase = get()
        )
    }
}