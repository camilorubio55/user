package com.justo.user.di.component

import android.app.Application
import com.justo.user.app.UserApplication
import com.justo.user.di.modules.ActivityBindingModule
import com.justo.user.di.modules.ApplicationModule
import com.justo.user.di.modules.FragmentBindingModule
import com.justo.user.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBindingModule::class, AndroidSupportInjectionModule::class, ApplicationModule::class, FragmentBindingModule::class,
        ViewModelModule::class
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