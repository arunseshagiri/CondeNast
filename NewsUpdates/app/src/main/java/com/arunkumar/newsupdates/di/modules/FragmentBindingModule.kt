package com.arunkumar.newsupdates.di.modules

import com.arunkumar.newsupdates.di.scopes.FragmentScope
import com.arunkumar.newsupdates.views.ArticleDetailFragment
import com.arunkumar.newsupdates.views.NewsUpdateFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [AdapterModule::class])
    abstract fun bindingNewsUpdateFragment(): NewsUpdateFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindingArticleDetailFragment(): ArticleDetailFragment
}
