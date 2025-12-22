package com.socialnetwork.mentis.di

import com.socialnetwork.mentis.data.remote.AuthApi
import com.socialnetwork.mentis.data.repository.AuthRepositoryImpl
import com.socialnetwork.mentis.domain.repository.AuthRepository
import com.socialnetwork.mentis.domain.use_case.LoginUseCase
import com.socialnetwork.mentis.domain.use_case.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return object : AuthApi {
            override suspend fun login(email: String, password: String) {}
            override suspend fun register(email: String, password: String) {}
        }
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(repository)
    }
}