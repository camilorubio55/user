package com.justo.user.di.modules

import com.justo.user.data.repository.IUserRepository
import com.justo.user.data.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(
        userRepository: UserRepository
    ): IUserRepository

}