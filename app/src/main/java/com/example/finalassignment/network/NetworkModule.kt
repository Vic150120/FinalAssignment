package com.example.finalassignment.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
// Module's Singleton making it available throughout the app
@InstallIn(SingletonComponent::class)

// OBJECT: NetworkModule
object NetworkModule {
    // Inform hilt, this function will add Dependency
    @Provides
    // One API is shared
    @Singleton
    // function to call for API
    fun provideApiService(): Service {
        // Provide RestfulApiDevService automatically via Hilt
        return Retrofit.serviceApi
    }
}
