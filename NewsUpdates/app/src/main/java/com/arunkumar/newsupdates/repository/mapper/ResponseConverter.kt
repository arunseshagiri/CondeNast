package com.arunkumar.newsupdates.repository.mapper

import com.arunkumar.newsupdates.NewsUpdateUtils.BASE_IMAGE_URL_DEFAULT
import com.arunkumar.newsupdates.NewsUpdateUtils.EMPTY_STRING
import com.arunkumar.newsupdates.NewsUpdateViewState
import com.arunkumar.newsupdates.models.NewsUpdateDomainModel
import com.arunkumar.newsupdates.models.RawNewsModel
import io.reactivex.functions.Function

class ResponseConverter : Function<RawNewsModel, NewsUpdateViewState> {
    override fun apply(newsResponse: RawNewsModel): NewsUpdateViewState {

        val articleList = ArrayList<NewsUpdateDomainModel>()
        newsResponse.articles?.forEach {
            val author = it.author ?: EMPTY_STRING
            val description = it.description ?: EMPTY_STRING
            val urlToImage = it.urlToImage ?: BASE_IMAGE_URL_DEFAULT
            val url = it.url ?: EMPTY_STRING
            val article = NewsUpdateDomainModel(author, description, urlToImage, url)

            articleList.add(article)
        }

        return NewsUpdateViewState.showNews(articleList)
    }
}
