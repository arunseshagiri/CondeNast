package com.arunkumar.newsupdates.repository

import com.arunkumar.newsupdates.viewstate.NewsUpdateViewState
import com.arunkumar.newsupdates.apiservice.ArticleApiService
import com.arunkumar.newsupdates.models.*
import com.arunkumar.newsupdates.repository.mapper.ListToViewStateConverter
import com.arunkumar.newsupdates.repository.mapper.ResponseConverter
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class NewsRepositoryTest {
    private lateinit var articleApiService: ArticleApiService
    private lateinit var converter: ResponseConverter
    private lateinit var viewStateConverter: ListToViewStateConverter

    private lateinit var newsRepository: NewsRepository

    private lateinit var articleList: List<NewsUpdateDomainModel>
    private lateinit var rawArticleList: List<Article>

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        articleApiService = mockk()
        converter = mockk()
        viewStateConverter = mockk()

        newsRepository = NewsRepository(articleApiService, converter, viewStateConverter)

        val source = Source("", "")
        val rawArticle1 = Article(
            source,
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )

        val rawArticle2 = Article(
            source,
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )

        val article1 = NewsUpdateDomainModel(
            "",
            "",
            "",
            "",
            "",
            "",
            "1",
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
            "2",
            20,
            30
        )

        rawArticleList = listOf(rawArticle1, rawArticle2)
        articleList = listOf(article1, article2)
    }

    @Test
    fun getNewsArticlesTestSuccess() {
        val rawArticle = RawNewsModel("status", 20, rawArticleList)
        val commentsModel1 = CommentsModel(20)
        val commentsModel2 = CommentsModel(30)
        val likesModel1 = LikesModel(10)
        val likesModel2 = LikesModel(20)
        every { articleApiService.articles(any()) } returns Single.just(rawArticle)

        every {
            articleApiService
                .articleComments("https://cn-news-info-api.herokuapp.com/comments/1")
        } returns Single.just(commentsModel1)
        every {
            articleApiService
                .articleComments("https://cn-news-info-api.herokuapp.com/comments/2")
        } returns Single.just(commentsModel2)

        every {
            articleApiService
                .articleLikes("https://cn-news-info-api.herokuapp.com/likes/1")
        } returns Single.just(likesModel1)
        every {
            articleApiService
                .articleLikes("https://cn-news-info-api.herokuapp.com/likes/2")
        } returns Single.just(likesModel2)

        every { converter.apply(rawArticle) } returns articleList

        every { viewStateConverter.apply(articleList) } returns
                NewsUpdateViewState.ShowNews(articleList)

        newsRepository
            .getNewsArticles("")
            .test()
            .assertComplete()
            .assertValue {
                (it as NewsUpdateViewState.ShowNews)
                    .newsUpdateDomainModel
                    .size == articleList.size
            }
            .assertValue {
                (it as NewsUpdateViewState.ShowNews)
                    .newsUpdateDomainModel[0]
                    .comments == articleList[0].comments
            }
    }

    @Test
    fun getNewsArticlesTestFailure() {
        val error = Throwable("Error")
        every { articleApiService.articles(any()) } returns Single.error(error)

        verify(exactly = 0) {
            articleApiService.articleComments(any())
            articleApiService.articleLikes(any())
            converter.apply(any())
            viewStateConverter.apply(articleList)
        }

        newsRepository
            .getNewsArticles("")
            .test()
            .assertError { it.message == error.message }
    }

    @Test
    fun `fetch news article is success however comments fetch failed`() {
        val article1 = NewsUpdateDomainModel(
            "",
            "",
            "",
            "",
            "",
            "",
            "1",
            10,
            0
        )

        val article2 = NewsUpdateDomainModel(
            "",
            "",
            "",
            "",
            "",
            "",
            "2",
            20,
            30
        )
        articleList = listOf(article1, article2)
        val rawArticle = RawNewsModel("status", 20, rawArticleList)
        val commentsModel2 = CommentsModel(30)
        val likesModel1 = LikesModel(10)
        val likesModel2 = LikesModel(20)

        val commentsError = Throwable("fetch comments failed")
        every { articleApiService.articles(any()) } returns Single.just(rawArticle)

        every {
            articleApiService
                .articleComments("https://cn-news-info-api.herokuapp.com/comments/1")
        } returns Single.error(commentsError)
        every {
            articleApiService
                .articleComments("https://cn-news-info-api.herokuapp.com/comments/2")
        } returns Single.just(commentsModel2)

        every {
            articleApiService
                .articleLikes("https://cn-news-info-api.herokuapp.com/likes/1")
        } returns Single.just(likesModel1)
        every {
            articleApiService
                .articleLikes("https://cn-news-info-api.herokuapp.com/likes/2")
        } returns Single.just(likesModel2)

        every { converter.apply(rawArticle) } returns articleList

        every { viewStateConverter.apply(articleList) } returns
                NewsUpdateViewState.ShowNews(articleList)

        newsRepository
            .getNewsArticles("")
            .test()
            .assertComplete()
            .assertValue {
                (it as NewsUpdateViewState.ShowNews)
                    .newsUpdateDomainModel
                    .size == articleList.size
            }
            .assertValue {
                (it as NewsUpdateViewState.ShowNews)
                    .newsUpdateDomainModel[0]
                    .comments == articleList[0].comments
            }
    }
}
