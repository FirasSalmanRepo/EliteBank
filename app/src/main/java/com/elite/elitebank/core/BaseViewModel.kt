package com.elite.elitebank.core

import androidx.lifecycle.ViewModel
import com.elite.elitebank.navigation.NavigationManager
import javax.inject.Inject

abstract class BaseViewModel<Event> : ViewModel() {

    @Inject
    lateinit var navigationManager: NavigationManager

    open fun onEvent(event: Event) = Unit
}
