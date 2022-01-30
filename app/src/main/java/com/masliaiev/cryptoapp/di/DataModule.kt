package com.masliaiev.cryptoapp.di

import android.app.Application
import com.masliaiev.cryptoapp.data.database.AppDatabase
import com.masliaiev.cryptoapp.data.database.CoinInfoDao
import com.masliaiev.cryptoapp.data.network.ApiFactory
import com.masliaiev.cryptoapp.data.network.ApiService
import com.masliaiev.cryptoapp.data.repository.CoinRepositoryImpl
import com.masliaiev.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object{

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(application: Application): CoinInfoDao{
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}