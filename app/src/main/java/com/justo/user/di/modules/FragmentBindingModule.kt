package com.justo.user.di.modules

import com.justo.user.view.user.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindUserFragment(): UserFragment
}