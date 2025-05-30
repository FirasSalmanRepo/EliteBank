package com.elite.elitebank.feature.onboarding.guide

sealed class GuideEvent {

    data object OnSkipClicked : GuideEvent()

    data object OnGetStartedClicked : GuideEvent()
}