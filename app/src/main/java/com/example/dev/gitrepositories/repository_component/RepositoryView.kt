package com.example.dev.gitrepositories.repository_component

import android.support.v7.widget.LinearLayoutManager
import com.example.dev.gitrepositories.models.Repositories
import com.example.dev.gitrepositories.models.Repository
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView

/**
 * Created by dev on 12.11.16..
 */
interface RepositoryView : MvpLceView<List<Repository>>{

    fun loadDefaultState()

    fun loadMore(layout: LinearLayoutManager)

    fun addData(data: Repositories?)

    fun searchRepos(): String

    fun loadEnteredData()

    fun openDetailsActivity(position: Int)

}