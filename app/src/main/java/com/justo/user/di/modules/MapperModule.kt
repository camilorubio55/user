package com.justo.user.di.modules

import com.justo.user.data.mapper.IMapper
import com.justo.user.data.mapper.Mapper
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun provideMapper(
        mapper: Mapper
    ): IMapper

}