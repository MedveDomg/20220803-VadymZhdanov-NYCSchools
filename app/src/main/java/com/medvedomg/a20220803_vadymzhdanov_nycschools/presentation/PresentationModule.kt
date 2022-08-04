package com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation

import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.schoollist.SchoolListViewModel
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainViewModel()
    }

    viewModel {
        SchoolListViewModel(interactor = get())
    }
}