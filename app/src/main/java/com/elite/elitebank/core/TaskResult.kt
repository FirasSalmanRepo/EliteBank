package com.elite.elitebank.core

typealias RootError = Error

/**
 * Serves as a container for any API or non-API requests. If wrapped around the responses,
 * makes it easier to handle errors and success scenarios differently.
 *
 * - out param `DATA` - the data to expect out from the wrapped Result
 * - out param `ERROR` - the error occurred during this Task
 */
sealed interface TaskResult<out DATA, out ERROR : RootError> {

    data class Success<out DATA, out ERROR : RootError>(
        val data: DATA
    ) : TaskResult<DATA, ERROR>

    data class Error<out DATA, out ERROR : RootError>(
        val error: ERROR
    ) : TaskResult<DATA, ERROR>
}

val <D, E : RootError> TaskResult<D, E>.isSuccessful: Boolean
    get() = this is TaskResult.Success

val <D, E : RootError> TaskResult<D, E>.errorOrNull: E?
    get() = if (this is TaskResult.Error) {
        this.error
    } else {
        null
    }
