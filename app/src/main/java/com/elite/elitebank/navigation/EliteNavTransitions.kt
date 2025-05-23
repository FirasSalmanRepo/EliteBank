package com.elite.elitebank.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

object EliteNavTransitions {

    private const val TRANSITION_DURATION = 350

    val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(TRANSITION_DURATION)
            )
        }

    val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        slideOutOfContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
            animationSpec = tween(TRANSITION_DURATION)
        )
    }

    val popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(TRANSITION_DURATION)
            )
        }

    val popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(TRANSITION_DURATION)
            )
        }
}
