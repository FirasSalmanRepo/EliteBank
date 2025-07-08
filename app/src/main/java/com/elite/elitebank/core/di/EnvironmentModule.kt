package com.elite.elitebank.core.di

import com.elite.elitebank.core.environment.EnvironmentImpl
import com.elite.elitebank.core.environment.IEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class EnvironmentModule {

    @Binds
    @Singleton
    abstract fun bindEnvironment(
        impl: EnvironmentImpl
    ): IEnvironment
}
