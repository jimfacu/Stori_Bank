package com.mobilenik.storibank.Data.Network.Helper

import android.content.Context
import com.mobilenik.storibank.Data.Network.DefaultRepository
import com.mobilenik.storibank.Data.Network.FirebaseRemoteDataSource
import com.mobilenik.storibank.Data.Network.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryHelper {

    @Volatile
    var repository: Repository? = null


    @Provides
    @Singleton
    fun provideRepository(@ApplicationContext context: Context): Repository {
        synchronized(this) {
            return repository ?: repository ?: createRepository(context)
        }
    }


    fun createRepository(@ApplicationContext context: Context): Repository {
        val newRepo =
            DefaultRepository(FirebaseRemoteDataSource())
        repository = newRepo
        return newRepo
    }
}