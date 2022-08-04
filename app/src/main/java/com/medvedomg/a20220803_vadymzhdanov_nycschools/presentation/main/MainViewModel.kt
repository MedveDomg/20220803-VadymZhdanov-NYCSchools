package com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.main

import androidx.lifecycle.ViewModel
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.util.SingleLiveEvent
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.util.asLiveData

class MainViewModel : ViewModel() {

    private val navigationActionMutableLiveData = SingleLiveEvent<Action>()
    val navigationActionLiveData = navigationActionMutableLiveData.asLiveData()

    init {
        navigationActionMutableLiveData.value = Action.StartAction
    }

    sealed class Action {

        object StartAction : Action()
    }
}