package com.medvedomg.a20220803_vadymzhdanov_nycschools

import android.app.Application
import com.medvedomg.a20220803_vadymzhdanov_nycschools.data.dataModule
import com.medvedomg.a20220803_vadymzhdanov_nycschools.domain.domainModule
import com.medvedomg.a20220803_vadymzhdanov_nycschools.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }

    companion object {
        val appModules = mutableListOf(
            dataModule,
            domainModule,
            presentationModule
        )
    }
}