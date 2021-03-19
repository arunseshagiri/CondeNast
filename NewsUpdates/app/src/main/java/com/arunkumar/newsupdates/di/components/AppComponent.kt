package com.arunkumar.newsupdates.di.components

import com.arunkumar.newsupdates.MainActivity
import com.arunkumar.newsupdates.MainApp
import com.arunkumar.newsupdates.di.modules.ActivityBindingModule
import com.arunkumar.newsupdates.di.modules.ApiModule
import com.arunkumar.newsupdates.di.modules.FragmentBindingModule
import com.arunkumar.newsupdates.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(
    modules = [AndroidInjectionModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
        NetworkModule::class,
        ApiModule::class]
)
interface AppComponent {
    fun inject(application: MainApp)

    fun inject(mainActivity: MainActivity)
}
