package com.arunkumar.newsupdates.repository

import com.arunkumar.newsupdates.NewsUpdateViewState
import com.arunkumar.newsupdates.apiservice.ArticleApiService
import com.arunkumar.newsupdates.repository.mapper.ResponseConverter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val articleApiService: ArticleApiService,
    private val converter: ResponseConverter
) {

    fun getNewsArticles(): Single<NewsUpdateViewState> {
        return articleApiService
            .articles()
            .map(converter)
            .subscribeOn(io())
    }
}
