package com.arunkumar.newsupdates.di.modules

import com.arunkumar.newsupdates.MainActivity
import com.arunkumar.newsupdates.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [FragmentBindingModule::class,
            ApiModule::class,
            ViewModelModule::class]
    )
    abstract fun bindingMainActivity(): MainActivity
}
