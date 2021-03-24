package com.arunkumar.newsupdates.repository

import com.arunkumar.newsupdates.NewsUpdateUtils.COMMENTS_PATH
import com.arunkumar.newsupdates.NewsUpdateUtils.EXTRA_INFO_BASEURL
import com.arunkumar.newsupdates.NewsUpdateUtils.LIKES_PATH
import com.arunkumar.newsupdates.NewsUpdateViewState
import com.arunkumar.newsupdates.apiservice.ArticleApiService
import com.arunkumar.newsupdates.models.CommentsModel
import com.arunkumar.newsupdates.models.LikesModel
import com.arunkumar.newsupdates.models.NewsUpdateDomainModel
import com.arunkumar.newsupdates.repository.mapper.ListToViewStateConverter
import com.arunkumar.newsupdates.repository.mapper.ResponseConverter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val articleApiService: ArticleApiService,
    private val converter: ResponseConverter,
    private val viewStateConverter: ListToViewStateConverter
) {
    fun getNewsArticles(url: String): Single<NewsUpdateViewState> {
        return articleApiService
            .articles(url)
            .map(converter)
            .toObservable()
            .flatMapIterable { articles -> articles }
            .flatMap { article -> getCommentsLikes(article) }
            .toList()
            .map(viewStateConverter)
            .subscribeOn(io())
    }

    private fun getCommentsLikes(article: NewsUpdateDomainModel): Observable<NewsUpdateDomainModel> {
        return Observable.zip(
            getNewsComments(article.articleId),
            getNewsLikes(article.articleId),
            { comments, likes ->
                NewsUpdateDomainModel(
                    article.title,
                    article.author,
                    article.description,
                    article.imageUrl,
                    article.url,
                    article.content,
                    article.articleId,
                    likes.likes,
                    comments.comments
                )
            }
        )
    }

    private fun getNewsComments(
        articleId: String
    ): Observable<CommentsModel> {
        return articleApiService
            .articleComments(EXTRA_INFO_BASEURL + COMMENTS_PATH + articleId)
            .toObservable()
            .onErrorReturn { CommentsModel(0) }
            .subscribeOn(io())
    }

    private fun getNewsLikes(
        articleId: String
    ): Observable<LikesModel> {
        return articleApiService
            .articleLikes(EXTRA_INFO_BASEURL + LIKES_PATH + articleId)
            .toObservable()
            .onErrorReturn { LikesModel(0) }
            .subscribeOn(io())
    }
}
