package com.arunkumar.newsupdates.di.components

import com.arunkumar.newsupdates.MainApp
import com.arunkumar.newsupdates.di.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [AndroidInjectionModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
        NetworkModule::class,
        ApiModule::class,
        AdapterModule::class]
)
interface AppComponent : AndroidInjector<MainApp> {
    override fun inject(application: MainApp)
}
