package com.example.dev.gitrepositories.repository_component

import com.example.dev.gitrepositories.adapters.RestAdapter
import com.example.dev.gitrepositories.models.Repositories
import rx.Observable

/**
 * Created by dev on 12.11.16..
 */
class RepositoryInteractor{

    fun getRepositoriesAsync(name: String, page: Int): Observable<Repositories> {
        val service = RestAdapter.getApiService
        return service.getRepositories(name, page)
    }
}