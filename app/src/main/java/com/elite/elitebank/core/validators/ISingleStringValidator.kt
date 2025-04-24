package com.elite.elitebank.core.validators

import com.elite.elitebank.core.RootError
import com.elite.elitebank.core.TaskResult


fun interface ISingleStringValidator {

    operator fun invoke(dataToValidate: String): TaskResult<Unit, RootError>
}
