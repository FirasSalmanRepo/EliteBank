package com.elite.elitebank.core.validators

import com.elite.elitebank.core.PasswordValidationError
import com.elite.elitebank.core.TaskResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoginPasswordValidator @Inject constructor() : ISingleStringValidator {

    override operator fun invoke(dataToValidate: String): TaskResult<Unit, PasswordValidationError> {

        if (dataToValidate.isBlank()) {
            return TaskResult.Error(PasswordValidationError.EMPTY)
        }

        if (dataToValidate.length < 8) {
            return TaskResult.Error(PasswordValidationError.TOO_SHORT)
        }

        if (dataToValidate.all { it.isLetterOrDigit() }) {
            return TaskResult.Error(PasswordValidationError.TOO_SIMPLE)
        }

        return TaskResult.Success(Unit)
    }
}
