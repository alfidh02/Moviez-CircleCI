package com.alfidh.moviez

import android.app.Application
import com.alfidh.moviez.core.di.databaseModule
import com.alfidh.moviez.core.di.networkModule
import com.alfidh.moviez.core.di.repositoryModule
import com.alfidh.moviez.di.useCaseModule
import com.alfidh.moviez.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}