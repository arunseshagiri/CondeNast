package com.arunkumar.newsupdates.apiservice

import com.arunkumar.newsupdates.models.CommentsModel
import com.arunkumar.newsupdates.models.LikesModel
import com.arunkumar.newsupdates.models.RawNewsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface ArticlesApiBackend {
    @GET
    fun articles(@Url url: String): Single<RawNewsModel>

    @GET
    fun articleComments(@Url url: String): Single<CommentsModel>

    @GET
    fun articleLikes(@Url url: String): Single<LikesModel>
}
