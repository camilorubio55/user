package com.justo.user.di.modules

import com.justo.user.data.dataSource.IUserLocalDataSource
import com.justo.user.data.dataSource.UserLocalDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class LocalDataSourceModule {

    @Binds
    abstract fun provideUserLocalDataSource(
        userLocalDataSource: UserLocalDataSource
    ): IUserLocalDataSource

}