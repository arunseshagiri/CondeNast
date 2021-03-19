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


    private val newsArticleLiveData: MutableLiveData<NewsUpdateViewState> = MutableLiveData()

    fun fetchNewsArticle() {
        disposable.add(
            repository
                .getNewsArticles()
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
