package com.elite.elitebank.feature.home

import com.elite.elitebank.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeEvent>() {


    private val _state = MutableStateFlow(
        HomeScreenState(
            userName =  "Firas  Salman"
        )
    )
    val state: StateFlow<HomeScreenState> get() = _state.asStateFlow()

    override fun onEvent(event: HomeEvent) {

    }

}
