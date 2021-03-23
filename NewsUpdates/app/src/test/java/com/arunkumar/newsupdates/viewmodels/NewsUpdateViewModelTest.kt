package com.arunkumar.newsupdates.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.arunkumar.newsupdates.NewsUpdateViewState
import com.arunkumar.newsupdates.models.NewsUpdateDomainModel
import com.arunkumar.newsupdates.repository.NewsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class NewsUpdateViewModelTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewsUpdateViewModel

    private lateinit var repository: NewsRepository
    private lateinit var disposable: CompositeDisposable

    private lateinit var articleList: List<NewsUpdateDomainModel>

    lateinit var observer: Observer<NewsUpdateViewState>

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        repository = mockk()
        disposable = spyk(CompositeDisposable())
        observer = mockk()

        viewModel = NewsUpdateViewModel(repository, disposable)

        val article1 = NewsUpdateDomainModel(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            10,
            20
        )

        val article2 = NewsUpdateDomainModel(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            20,
            30
        )

        articleList = listOf(article1, article2)
    }

    @Test
    fun fetchNewsArticleSuccessTest() {
        val articleResponse = NewsUpdateViewState.ShowNews(articleList)
        every { repository.getNewsArticles(any()) } returns
                Single.just(articleResponse)

        val slot = slot<NewsUpdateViewState>()
        val list = arrayListOf<NewsUpdateViewState>()

        viewModel.newsArticleLiveData.observeForever(observer)

        every { observer.onChanged(capture(slot)) } answers {
            list.add(slot.captured)
        }

        viewModel.fetchNewsArticle()

        assertEquals(list[0], NewsUpdateViewState.ShowProgress)
        assertEquals(list[1], NewsUpdateViewState.HideProgress)
        assertEquals(list[2], articleResponse)
    }

    @Test
    fun fetchNewsArticleFailureTest() {
        val articleResponse = NewsUpdateViewState.Error(Throwable("Some error"))
        every { repository.getNewsArticles(any()) } returns
                Single.just(articleResponse)

        val slot = slot<NewsUpdateViewState>()
        val list = arrayListOf<NewsUpdateViewState>()

        viewModel.newsArticleLiveData.observeForever(observer)

        every { observer.onChanged(capture(slot)) } answers {
            list.add(slot.captured)
        }

        viewModel.fetchNewsArticle()

        assertEquals(list[0], NewsUpdateViewState.ShowProgress)
        assertEquals(list[1], NewsUpdateViewState.HideProgress)
        assertEquals(list[2], articleResponse)
    }

    @Test
    fun `once fetched successfully next fetch shouldnt happen`() {
        val articleResponse = NewsUpdateViewState.ShowNews(articleList)
        every { repository.getNewsArticles(any()) } returns
                Single.just(articleResponse)

        val slot = slot<NewsUpdateViewState>()
        val list = arrayListOf<NewsUpdateViewState>()

        viewModel.newsArticleLiveData.observeForever(observer)

        every { observer.onChanged(capture(slot)) } answers {
            list.add(slot.captured)
        }

        viewModel.articleList = articleList

        viewModel.fetchNewsArticle()

        assertEquals(list.size, 0)
    }
}
