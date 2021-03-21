package com.arunkumar.newsupdates.repository.mapper

import com.arunkumar.newsupdates.models.NewsUpdateDomainModel
import io.reactivex.functions.Function

class CommentsResponseConverter :
    Function<Pair<Int, NewsUpdateDomainModel>, NewsUpdateDomainModel> {
    override fun apply(pair: Pair<Int, NewsUpdateDomainModel>): NewsUpdateDomainModel {
        val comments = pair.first
        val article = pair.second

        return NewsUpdateDomainModel(
            article.author,
            article.description,
            article.imageUrl,
            article.imageUrl,
            article.articleId,
            0,
            comments
        )
    }
}
