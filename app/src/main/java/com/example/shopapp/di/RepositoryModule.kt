package com.example.shopapp.di

import com.example.shopapp.data.repository.ShopRepositoryImpl
import com.example.shopapp.domain.repository.ShopRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideShopRepository(shopRepositoryImpl: ShopRepositoryImpl): ShopRepository
}