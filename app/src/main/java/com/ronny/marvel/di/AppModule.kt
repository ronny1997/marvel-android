package com.ronny.marvel.di

import android.content.Context
import com.ronny.marvel.BuildConfig
import com.ronny.marvel.common.constans.Constants
import com.ronny.marvel.data.local.AppRoomDatabase
import com.ronny.marvel.data.local.marvel.MarvelLocalDataSource
import com.ronny.marvel.data.local.marvel.MarvelLocalDataSourceImpl
import com.ronny.marvel.data.local.marvel.dao.MarvelDataDao
import com.ronny.marvel.data.remote.MarvelRemoteDataSource
import com.ronny.marvel.data.remote.MarvelRemoteDataSourceImpl
import com.ronny.marvel.data.repository.CharacterRepositoryImpl
import com.ronny.marvel.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun appRoomDatabaseProvider(@ApplicationContext context: Context) =
        AppRoomDatabase.buildDatabase(context)

    @Provides
    fun marvelDataDaoProvider(dataBase: AppRoomDatabase) = dataBase.marvelDataDao()

    @Provides
    fun marvelLocalDataSourceProvider(marvelDataDao: MarvelDataDao): MarvelLocalDataSource =
        MarvelLocalDataSourceImpl(marvelDataDao)

    @Provides
    @Singleton
    fun marvelRemoteDataSourceProvider(retrofit: Retrofit): MarvelRemoteDataSource =
        MarvelRemoteDataSourceImpl(retrofit)


    @Provides
    @Singleton
    fun provideCharacterRepository(dataSource: CharacterRepositoryImpl): CharacterRepository =
        dataSource

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }
}