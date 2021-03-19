package com.arunkumar.newsupdates.apiservice

import com.arunkumar.newsupdates.models.RawNewsModel
import io.reactivex.Single
import retrofit2.http.GET

interface ArticlesApiBackend {
    @GET("https://newsapi.org/v2/top-headlines?country=us&apiKey=ca03e0d6656148cbaf18a2ce6cee8d6e")
    fun articles(): Single<RawNewsModel>
}
