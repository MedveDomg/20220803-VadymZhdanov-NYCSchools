package com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.schoollist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medvedomg.a20220803_vadymzhdanov_nycschools.domain.SchoolListInteractor
import com.medvedomg.a20220803_vadymzhdanov_nycschools.domain.util.Result
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.util.ViewState
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.util.asLiveData
import kotlinx.coroutines.launch

class SchoolListViewModel(
    private val interactor: SchoolListInteractor
) : ViewModel() {

    private val _viewStateLiveData =
        MutableLiveData<ViewState<List<SchoolModel>>>(ViewState.Loading)
    val viewStateLiveData = _viewStateLiveData.asLiveData()

    init {
        viewModelScope.launch {
            when (val result = interactor.getSchoolList()) {
                is Result.Success -> {
                    val viewState = ViewState.Success(result.data)
                    _viewStateLiveData.postValue(viewState)
                }
                is Result.Error -> {
                    val viewState = ViewState.Error<List<SchoolModel>>(
                        error = result.error,
                        errorMessage = result.error.message
                    )
                    _viewStateLiveData.postValue(viewState)
                }
            }
        }
    }
}