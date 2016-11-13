package com.example.dev.gitrepositories.modules

import android.content.Context
import com.example.dev.gitrepositories.api.ApiService
import com.example.dev.gitrepositories.models.Repository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by dev on 12.11.16..
 */
@Module
class AppModule (val context: Context){

    @Provides
    fun providesContext(): Context{
        return context
    }

    @Provides
    fun providesData(): MutableList<Repository>{
        return ArrayList()
    }

    @Provides
    fun providesApiService(): ApiService{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level= HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://")
                .client(client)
                .build()

        return retrofit.create(ApiService::class.java)
    }

}

