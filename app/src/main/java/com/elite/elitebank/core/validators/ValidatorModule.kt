package com.elite.elitebank.core.validators

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ValidatorModule {

    @Binds
    @Singleton
    @Named("email")
    internal abstract fun bindEmailValidator(
        loginEmailValidator: LoginEmailValidator
    ): ISingleStringValidator

    @Binds
    @Singleton
    @Named("password")
    internal abstract fun bindPasswordValidator(
        loginPasswordValidator: LoginPasswordValidator
    ): ISingleStringValidator
}
