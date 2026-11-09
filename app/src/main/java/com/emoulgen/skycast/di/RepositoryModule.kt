package com.emoulgen.skycast.di

import com.emoulgen.skycast.data.repository.CityRepositoryImpl
import com.emoulgen.skycast.domain.repository.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCityRepository(impl: CityRepositoryImpl): CityRepository
}