package com.arunkumar.newsupdates.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arunkumar.newsupdates.BuildConfig
import com.arunkumar.newsupdates.viewstate.NewsUpdateViewState
import com.arunkumar.newsupdates.models.NewsUpdateDomainModel
import com.arunkumar.newsupdates.repository.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsUpdateViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val disposable: CompositeDisposable
) : ViewModel() {

    val newsArticleLiveData: MutableLiveData<NewsUpdateViewState> = MutableLiveData()
    var articleList: List<NewsUpdateDomainModel> = ArrayList()
    var selectedPosition: Int = -1

    fun fetchNewsArticle() {
        if (articleList.isNotEmpty()) {
            return
        }
        disposable.add(
            repository
                .getNewsArticles(
                    "https://newsapi.org/v2/top-headlines?country=us&${BuildConfig.API_KEY}"
                )
                .observeOn(mainThread())
                .doOnSubscribe { newsArticleLiveData.value = NewsUpdateViewState.ShowProgress }
                .doOnEvent { _, _ -> newsArticleLiveData.value = NewsUpdateViewState.HideProgress }
                .subscribe(
                    {
                        newsArticleLiveData.value = it
                    },
                    {
                        newsArticleLiveData.value = NewsUpdateViewState.Error(it)
                    }
                )
        )
    }
}
