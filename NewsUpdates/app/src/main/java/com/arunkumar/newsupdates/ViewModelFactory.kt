package com.arunkumar.newsupdates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arunkumar.newsupdates.repository.NewsRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: NewsRepository,
    private val disposable: CompositeDisposable
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(NewsRepository::class.java, CompositeDisposable::class.java)
            .newInstance(repository, disposable)
    }
}
