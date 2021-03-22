package com.arunkumar.newsupdates.di.modules

import com.arunkumar.newsupdates.adapters.NewsArticleAdapter
import com.arunkumar.newsupdates.di.scopes.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @FragmentScope
    @Provides
    fun providesAdapter(): NewsArticleAdapter {
        return NewsArticleAdapter()
    }
}
