package com.example.dev.gitrepositories.repository_component

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.example.dev.gitrepositories.modules.AppModule
import com.example.dev.gitrepositories.modules.FragmentModule
import com.example.dev.gitrepositories.modules.LayoutModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by dev on 12.11.16..
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, FragmentModule::class, LayoutModule::class))
interface RepositoryComponent{

    fun inject(context: Context)

    fun presenter(): RepositoryPresenter

    fun layout(): LinearLayoutManager

    fun adapter(): RepositoryAdapter

}