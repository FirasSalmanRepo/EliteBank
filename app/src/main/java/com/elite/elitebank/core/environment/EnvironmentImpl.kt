package com.elite.elitebank.core.environment

import android.content.Context
import com.elite.elitebank.BuildConfig
import com.elite.elitebank.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
internal class EnvironmentImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : IEnvironment {

    override val appName: String
        get() = context.getString(R.string.app_name)

    override val version: String
        get() = BuildConfig.VERSION_NAME
}
