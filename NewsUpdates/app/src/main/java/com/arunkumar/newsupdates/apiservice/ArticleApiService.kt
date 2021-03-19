package com.arunkumar.newsupdates.apiservice

import com.arunkumar.newsupdates.models.RawNewsModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class ArticleApiService @Inject constructor(private val articlesApiBackend: ArticlesApiBackend) {

    fun articles(): Single<RawNewsModel> {
        return articlesApiBackend
            .articles()
            .subscribeOn(io())
    }
}
