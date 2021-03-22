package com.arunkumar.newsupdates.di.modules

import androidx.lifecycle.ViewModelProvider
import com.arunkumar.newsupdates.ViewModelFactory
import com.arunkumar.newsupdates.apiservice.ArticleApiService
import com.arunkumar.newsupdates.di.scopes.ActivityScope
import com.arunkumar.newsupdates.repository.NewsRepository
import com.arunkumar.newsupdates.repository.mapper.ListToViewStateConverter
import com.arunkumar.newsupdates.repository.mapper.ResponseConverter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ViewModelModule {

    @ActivityScope
    @Provides
    fun providesDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @ActivityScope
    @Provides
    fun providesNewsRepository(
        articleApiService: ArticleApiService,
        converter: ResponseConverter,
        viewStateConverter: ListToViewStateConverter
    ): NewsRepository {
        return NewsRepository(
            articleApiService,
            converter,
            viewStateConverter
        )
    }

    @ActivityScope
    @Provides
    fun providesViewModelFactory(
        repository: NewsRepository,
        disposable: CompositeDisposable
    ): ViewModelProvider.Factory {
        return ViewModelFactory(repository, disposable)
    }
}
