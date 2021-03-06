package com.justo.user.di.modules

import com.justo.user.domain.useCases.GetUsersUseCase
import com.justo.user.domain.useCases.UpdateUserListUseCase
import com.justo.user.domain.useCases.IGetUsersUseCase
import com.justo.user.domain.useCases.UpdateUserSelectionStatusUseCase
import com.justo.user.domain.useCases.IUpdateUserSelectionStatusUseCase
import com.justo.user.domain.useCases.IUpdateUserCheckedUseCase
import com.justo.user.domain.useCases.UpdateUserCheckedUseCase
import com.justo.user.domain.useCases.DeleteUsersSelectedUseCase
import com.justo.user.domain.useCases.IDeleteUsersSelectedUseCase
import com.justo.user.domain.useCases.IUpdateUserListUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UseCasesModule {

    @Binds
    abstract fun provideDeleteUsersSelectedUseCase(deleteUsersSelectedUseCase: DeleteUsersSelectedUseCase): IDeleteUsersSelectedUseCase

    @Binds
    abstract fun provideGetGetUsersUseCase(getGetUsersUseCase: GetUsersUseCase): IGetUsersUseCase

    @Binds
    abstract fun provideUpdateUserListUseCase(updateUserListUseCase: UpdateUserListUseCase): IUpdateUserListUseCase

    @Binds
    abstract fun provideUpdateUserSelectionStatusUseCase(updateUserSelectionStatusUseCase: UpdateUserSelectionStatusUseCase): IUpdateUserSelectionStatusUseCase

    @Binds
    abstract fun provideUpdateUserCheckedUseCase(updateUpdateUserCheckedUseCase: UpdateUserCheckedUseCase): IUpdateUserCheckedUseCase
}