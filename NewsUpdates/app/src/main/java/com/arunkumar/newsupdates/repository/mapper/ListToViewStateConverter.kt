package com.arunkumar.newsupdates.repository.mapper

import com.arunkumar.newsupdates.NewsUpdateViewState
import com.arunkumar.newsupdates.models.NewsUpdateDomainModel
import io.reactivex.functions.Function

class ListToViewStateConverter : Function<List<NewsUpdateDomainModel>, NewsUpdateViewState> {
    override fun apply(articleList: List<NewsUpdateDomainModel>): NewsUpdateViewState {
        return NewsUpdateViewState.ShowNews(articleList)
    }
}
