package com.arunkumar.newsupdates.di.modules

import com.arunkumar.newsupdates.apiservice.ArticleApiService
import com.arunkumar.newsupdates.di.scopes.ActivityScope
import com.arunkumar.newsupdates.repository.NewsRepository
import com.arunkumar.newsupdates.repository.mapper.ListToViewStateConverter
import com.arunkumar.newsupdates.repository.mapper.ResponseConverter
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

    @ActivityScope
    @Provides
    fun providesResponseConverter(): ResponseConverter {
        return ResponseConverter()
    }

    @ActivityScope
    @Provides
    fun providesViewStateResponseConverter(): ListToViewStateConverter {
        return ListToViewStateConverter()
    }
}
