package com.arunkumar.newsupdates

import androidx.multidex.MultiDex
import com.arunkumar.newsupdates.di.components.AppComponent
import com.arunkumar.newsupdates.di.components.DaggerAppComponent
import com.arunkumar.newsupdates.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MainApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent: AppComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
        return appComponent
    }
}
