package com.arunkumar.newsupdates.repository.mapper

import com.arunkumar.newsupdates.models.Article
import com.arunkumar.newsupdates.models.RawNewsModel
import com.arunkumar.newsupdates.models.Source
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResponseConverterTest {

    private lateinit var rawArticleList: List<Article>

    @Before
    fun setup() {
        val source = Source("", "")
        val rawArticle1 = Article(
            source,
            "author 1",
            "",
            "",
            "",
            "",
            "",
            ""
        )

        val rawArticle2 = Article(
            source,
            "author 2",
            "",
            "",
            "",
            "",
            "",
            ""
        )

        rawArticleList = listOf(rawArticle1, rawArticle2)
    }

    @Test
    fun converterTest() {
        val rawArticle = RawNewsModel("status", 20, rawArticleList)
        val converter = ResponseConverter()

        val articleList = converter.apply(rawArticle)

        assertEquals(rawArticleList.size, articleList.size)

        assertEquals(rawArticleList[0].author, articleList[0].author)
        assertEquals(rawArticleList[1].author, articleList[1].author)
    }
}
