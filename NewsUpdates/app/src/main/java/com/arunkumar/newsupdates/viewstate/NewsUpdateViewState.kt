package com.arunkumar.newsupdates.viewstate

import com.arunkumar.newsupdates.models.NewsUpdateDomainModel

sealed class NewsUpdateViewState {
    object ShowProgress : NewsUpdateViewState()

    object HideProgress : NewsUpdateViewState()

    class ShowNews(val newsUpdateDomainModel: List<NewsUpdateDomainModel>) : NewsUpdateViewState()

    class Error(val error: Throwable) : NewsUpdateViewState()
}
