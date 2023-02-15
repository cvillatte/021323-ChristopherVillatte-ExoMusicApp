package com.ChristopherVillatte.exomusicapp.dependencyinjection

import com.ChristopherVillatte.exomusicapp.rest.ExoMusicRepository
import com.ChristopherVillatte.exomusicapp.rest.ExoMusicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesExoMusicRepositoryImpl(exoMusicRepositoryImpl: ExoMusicRepositoryImpl) : ExoMusicRepository
}