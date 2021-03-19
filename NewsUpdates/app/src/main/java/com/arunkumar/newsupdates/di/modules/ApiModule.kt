package com.arunkumar.newsupdates.di.modules

import androidx.lifecycle.ViewModelProvider
import com.arunkumar.newsupdates.ViewModelFactory
import com.arunkumar.newsupdates.apiservice.ArticleApiService
import com.arunkumar.newsupdates.repository.NewsRepository
import com.arunkumar.newsupdates.repository.mapper.ResponseConverter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ApiModule {

    @Provides
    fun providesResponseConverter(): ResponseConverter {
        return ResponseConverter()
    }

    @Provides
    fun providesNewsRepository(
        articleApiService: ArticleApiService,
        converter: ResponseConverter
    ): NewsRepository {
        return NewsRepository(articleApiService, converter)
    }

    @Provides
    fun providesDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun providesViewModelFactory(
        repository: NewsRepository,
        disposable: CompositeDisposable
    ): ViewModelProvider.Factory {
        return ViewModelFactory(repository, disposable)
    }
}
