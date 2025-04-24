package com.elite.elitebank.ui.theme.component.bottombar

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ToggleableBottomBarState {

    private val _visibleState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val visibleState: StateFlow<Boolean> get() = _visibleState

    fun show() {
        _visibleState.update { true }
    }

    fun hide() {
        _visibleState.update { false }
    }

    fun toggle() {
        _visibleState.update { !_visibleState.value }
    }
}

val LocalToggleBottomBarState: ProvidableCompositionLocal<ToggleableBottomBarState> =
    compositionLocalOf {
        ToggleableBottomBarState()
    }
