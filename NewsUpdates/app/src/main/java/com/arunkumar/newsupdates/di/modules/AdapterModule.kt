package com.arunkumar.newsupdates.di.modules

import com.arunkumar.newsupdates.adapters.NewsArticleAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    fun providesAdapter(): NewsArticleAdapter {
        return NewsArticleAdapter()
    }
}
