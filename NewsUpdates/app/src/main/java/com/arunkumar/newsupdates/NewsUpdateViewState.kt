package com.arunkumar.newsupdates

import com.arunkumar.newsupdates.models.NewsUpdateDomainModel

sealed class NewsUpdateViewState {
    object showProgress : NewsUpdateViewState()

    object hideProgress : NewsUpdateViewState()

    class showNews(val newsUpdateDomainModel: List<NewsUpdateDomainModel>) : NewsUpdateViewState()

    class error(val error: Throwable) : NewsUpdateViewState()
}
