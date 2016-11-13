package com.example.dev.gitrepositories.mainactivity_component

import android.content.Context
import com.example.dev.gitrepositories.modules.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by dev on 12.11.16..
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface MainActivityComponent{

    fun inject(context: Context)

    fun presenter(): MainActivityPresenter
}