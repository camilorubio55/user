package com.justo.user.di.modules

import com.justo.user.domain.useCases.GetUserUseCase
import com.justo.user.domain.useCases.IGetUserUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UseCasesModule {

    @Binds
    abstract fun provideGetUserUseCase(getUserUseCase: GetUserUseCase): IGetUserUseCase
}