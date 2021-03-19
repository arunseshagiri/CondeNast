package com.arunkumar.newsupdates.di.modules

import com.arunkumar.newsupdates.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentBindingModule::class])
    abstract fun bindingMainActivity(): MainActivity
}
