package com.arunkumar.newsupdates.di.components

import com.arunkumar.newsupdates.MainApp
import com.arunkumar.newsupdates.di.modules.ActivityBindingModule
import com.arunkumar.newsupdates.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ActivityBindingModule::class,
        NetworkModule::class]
)
interface AppComponent : AndroidInjector<MainApp> {
    override fun inject(application: MainApp)
}
