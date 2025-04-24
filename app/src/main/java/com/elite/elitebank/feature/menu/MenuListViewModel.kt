package com.elite.elitebank.feature.menu

import com.elite.elitebank.core.BaseViewModel
import com.elite.elitebank.core.environment.IEnvironment
import com.elite.elitebank.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MenuListViewModel @Inject constructor(
    mEnvironment: IEnvironment,
) : BaseViewModel<MenuListEvent>() {

    val state: StateFlow<MenuListScreenState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow(
        MenuListScreenState(
            userName = "Firas Salman",
            emailId = "FirasSalman.93@gmail.com",
            appName = mEnvironment.appName,
            appVersion = mEnvironment.version
        )
    )

    override fun onEvent(event: MenuListEvent) {
        when (event) {
            is MenuListEvent.OnItemClicked -> {
                when (event.item) {
                    MoreListItem.PROFILE -> {

                    }

                    MoreListItem.SAVED_LOCATIONS -> {

                    }

                    MoreListItem.FAQ -> {
                        navigationManager.navigate(NavRoute.DETAILS.AQI_SCALE)
                    }

                    MoreListItem.SETTINGS -> {

                    }

                    MoreListItem.ABOUT_US -> {

                    }

                    MoreListItem.CONTACT_US -> {

                    }

                    MoreListItem.LOGOUT -> {
                        _state.update {
                            it.copy(
                                isConfirmLogoutPopupVisible = true
                            )
                        }
                    }
                }
            }

            is MenuListEvent.OnConfirmLogoutPopupAction -> {
                _state.update {
                    it.copy(
                        isConfirmLogoutPopupVisible = false
                    )
                }
            }
        }
    }
}
