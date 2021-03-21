package com.arunkumar.newsupdates.di.modules

import androidx.lifecycle.ViewModelProvider
import com.arunkumar.newsupdates.ViewModelFactory
import com.arunkumar.newsupdates.apiservice.ArticleApiService
import com.arunkumar.newsupdates.repository.NewsRepository
import com.arunkumar.newsupdates.repository.mapper.CommentsResponseConverter
import com.arunkumar.newsupdates.repository.mapper.LikesResponseConverter
import com.arunkumar.newsupdates.repository.mapper.ListToViewStateConverter
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
    fun providesCommentsResponseConverter(): CommentsResponseConverter {
        return CommentsResponseConverter()
    }

    @Provides
    fun providesLikesResponseConverter(): LikesResponseConverter {
        return LikesResponseConverter()
    }

    @Provides
    fun providesViewStateResponseConverter(): ListToViewStateConverter {
        return ListToViewStateConverter()
    }

    @Provides
    fun providesNewsRepository(
        articleApiService: ArticleApiService,
        converter: ResponseConverter,
        commentsResponseConverter: CommentsResponseConverter,
        likesResponseConverter: LikesResponseConverter,
        viewStateConverter: ListToViewStateConverter
    ): NewsRepository {
        return NewsRepository(
            articleApiService,
            converter,
            commentsResponseConverter,
            likesResponseConverter,
            viewStateConverter
        )
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
