package com.arunkumar.newsupdates.di.modules

import com.arunkumar.newsupdates.apiservice.ArticlesApiBackend
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    fun providesBuildLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder().setPrettyPrinting().create()
    }

    @Provides
    fun providesOkHttpClient(buildLogger: HttpLoggingInterceptor): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(buildLogger)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build()

    @Provides
    fun providesRetrofitBuilder(
        @Named("articleBaseUrl") baseUrl: String,
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesArticlesApiBackend(retrofit: Retrofit): ArticlesApiBackend {
        return retrofit.create(ArticlesApiBackend::class.java)
    }

    @Provides
    @Named("articleBaseUrl")
    fun baseUrl(): String {
        return "https://newsapi.org/v2/"
    }
}
