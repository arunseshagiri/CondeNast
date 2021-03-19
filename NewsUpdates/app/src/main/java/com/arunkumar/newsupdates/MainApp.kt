package com.arunkumar.newsupdates

import android.app.Activity
import androidx.multidex.MultiDexApplication
import com.arunkumar.newsupdates.di.components.AppComponent
import com.arunkumar.newsupdates.di.components.DaggerAppComponent
import com.arunkumar.newsupdates.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MainApp : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appComponent = createAppComponent()
        appComponent.inject(this)
    }

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent
            .builder()
            .networkModule(NetworkModule("https://newsapi.org/v2/"))
            .build()
    }

    fun getComponent(): AppComponent {
        return appComponent
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}
