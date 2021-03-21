package com.arunkumar.newsupdates.apiservice

import com.arunkumar.newsupdates.models.CommentsModel
import com.arunkumar.newsupdates.models.LikesModel
import com.arunkumar.newsupdates.models.RawNewsModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class ArticleApiService @Inject constructor(private val articlesApiBackend: ArticlesApiBackend) {

    fun articles(url: String): Single<RawNewsModel> {
        return articlesApiBackend
            .articles(url)
            .subscribeOn(io())
    }

    fun articleComments(url: String): Single<CommentsModel> {
        return articlesApiBackend
            .articleComments(url)
            .subscribeOn(io())
    }

    fun articleLikes(url: String): Single<LikesModel> {
        return articlesApiBackend
            .articleLikes(url)
            .subscribeOn(io())
    }
}
