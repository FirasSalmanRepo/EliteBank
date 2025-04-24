package com.elite.elitebank.feature.menu

sealed class MenuListEvent {

    data class OnItemClicked(val item: MoreListItem) : MenuListEvent()

    data class OnConfirmLogoutPopupAction(val isConfirm: Boolean) : MenuListEvent()
}

enum class MoreListItem {

    PROFILE,

    SAVED_LOCATIONS,

    FAQ,

    SETTINGS,

    ABOUT_US,

    CONTACT_US,

    LOGOUT
}
