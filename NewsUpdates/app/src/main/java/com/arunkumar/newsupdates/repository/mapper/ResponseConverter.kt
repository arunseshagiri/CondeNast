package com.arunkumar.newsupdates.repository.mapper

import com.arunkumar.newsupdates.NewsUpdateUtils.BASE_IMAGE_URL_DEFAULT
import com.arunkumar.newsupdates.NewsUpdateUtils.EMPTY_STRING
import com.arunkumar.newsupdates.models.NewsUpdateDomainModel
import com.arunkumar.newsupdates.models.RawNewsModel
import io.reactivex.functions.Function
import timber.log.Timber

class ResponseConverter : Function<RawNewsModel, List<NewsUpdateDomainModel>> {
    override fun apply(newsResponse: RawNewsModel): List<NewsUpdateDomainModel> {

        val articleList = ArrayList<NewsUpdateDomainModel>()
        newsResponse.articles?.forEach {
            val title = it.title ?: EMPTY_STRING
            val author = it.author ?: EMPTY_STRING
            val description = it.description ?: EMPTY_STRING
            val urlToImage = it.urlToImage ?: BASE_IMAGE_URL_DEFAULT
            val url = it.url ?: EMPTY_STRING
            val content = it.content ?: EMPTY_STRING

            val urlWithoutScheme = url
                .replace("http://", EMPTY_STRING)
                .replace("https://", EMPTY_STRING)
            val articleId = urlWithoutScheme.replace("/", "-")

            Timber.d("articleId: $articleId")

            val article = NewsUpdateDomainModel(
                title,
                author,
                description,
                urlToImage,
                url,
                content,
                articleId,
                0,
                0
            )
            articleList.add(article)
        }

        return articleList
    }
}
