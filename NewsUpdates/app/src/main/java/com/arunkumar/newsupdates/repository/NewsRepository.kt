package com.arunkumar.newsupdates.repository

import com.arunkumar.newsupdates.NewsUpdateUtils.COMMENTS_PATH
import com.arunkumar.newsupdates.NewsUpdateUtils.EXTRA_INFO_BASEURL
import com.arunkumar.newsupdates.NewsUpdateUtils.LIKES_PATH
import com.arunkumar.newsupdates.NewsUpdateViewState
import com.arunkumar.newsupdates.apiservice.ArticleApiService
import com.arunkumar.newsupdates.models.NewsUpdateDomainModel
import com.arunkumar.newsupdates.repository.mapper.CommentsResponseConverter
import com.arunkumar.newsupdates.repository.mapper.LikesResponseConverter
import com.arunkumar.newsupdates.repository.mapper.ListToViewStateConverter
import com.arunkumar.newsupdates.repository.mapper.ResponseConverter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val articleApiService: ArticleApiService,
    private val converter: ResponseConverter,
    private val commentsConverter: CommentsResponseConverter,
    private val likesConverter: LikesResponseConverter,
    private val viewStateConverter: ListToViewStateConverter
) {
    fun getNewsArticles(url: String): Single<NewsUpdateViewState> {
        return articleApiService
            .articles(url)
            .map(converter)
            .toObservable()
            .flatMapIterable { articles -> articles }
            .flatMap { article -> getsNewsComments(article, article.articleId) }
            .flatMap { article -> getsNewsLikes(article, article.articleId) }
            .toList()
            .map(viewStateConverter)
            .subscribeOn(io())
    }

    private fun getsNewsComments(
        article: NewsUpdateDomainModel,
        articleId: String
    ): Observable<NewsUpdateDomainModel> {
        return articleApiService
            .articleComments(EXTRA_INFO_BASEURL + COMMENTS_PATH + articleId)
            .map { comments -> Pair(comments.comments, article) }
            .map(commentsConverter)
            .toObservable()
            .subscribeOn(io())
    }

    private fun getsNewsLikes(
        article: NewsUpdateDomainModel,
        articleId: String
    ): Observable<NewsUpdateDomainModel> {
        return articleApiService
            .articleLikes(EXTRA_INFO_BASEURL + LIKES_PATH + articleId)
            .map { likes -> Pair(likes.likes, article) }
            .map(likesConverter)
            .toObservable()
            .subscribeOn(io())
    }
}
