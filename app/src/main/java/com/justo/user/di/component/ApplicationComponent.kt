package com.justo.user.di.component

import android.app.Application
import com.justo.user.app.UserApplication
import com.justo.user.di.modules.ActivityBindingModule
import com.justo.user.di.modules.FragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class, ActivityBindingModule::class, FragmentBindingModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<UserApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun create(application: Application): Builder

        fun build(): ApplicationComponent
    }
}