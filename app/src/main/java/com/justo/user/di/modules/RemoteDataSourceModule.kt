package com.justo.user.di.modules

import com.justo.user.data.dataSource.IUserRemoteDataSource
import com.justo.user.data.dataSource.UserRemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun provideUserRemoteDataSource(
        userRemoteDataSource: UserRemoteDataSource
    ): IUserRemoteDataSource

}