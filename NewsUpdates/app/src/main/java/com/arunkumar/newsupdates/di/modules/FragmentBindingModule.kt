package com.arunkumar.newsupdates.di.modules

import com.arunkumar.newsupdates.views.NewsUpdateFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun bindingNewsUpdateFragment(): NewsUpdateFragment
}
