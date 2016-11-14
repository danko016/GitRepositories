package com.example.dev.gitrepositories.api

import com.example.dev.gitrepositories.models.Repositories
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by dev on 12.11.16..
 */
interface ApiService{

    @GET("/search/repositories")
    fun getRepositories(@Query("q") name: String, @Query("page") page: Int): Observable<Repositories>
}