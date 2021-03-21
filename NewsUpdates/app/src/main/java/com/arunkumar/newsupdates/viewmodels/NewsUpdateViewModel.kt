package com.arunkumar.newsupdates.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arunkumar.newsupdates.NewsUpdateViewState
import com.arunkumar.newsupdates.repository.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsUpdateViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val disposable: CompositeDisposable
) : ViewModel() {


    val newsArticleLiveData: MutableLiveData<NewsUpdateViewState> = MutableLiveData()

    fun fetchNewsArticle() {
        disposable.add(
            repository
                .getNewsArticles("https://newsapi.org/v2/top-headlines?country=us&apiKey=ca03e0d6656148cbaf18a2ce6cee8d6e")
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
