package com.justo.user.di.modules

import com.justo.user.domain.useCases.GetUsersUseCase
import com.justo.user.domain.useCases.UpdateUserListUseCase
import com.justo.user.domain.useCases.IGetUsersUseCase
import com.justo.user.domain.useCases.IUpdateListUserUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UseCasesModule {

    @Binds
    abstract fun provideGetUserFromLocal(getGetUsersUseCase: GetUsersUseCase): IGetUsersUseCase

    @Binds
    abstract fun provideGetUserUseCase(updateUserListUseCase: UpdateUserListUseCase): IUpdateListUserUseCase
}