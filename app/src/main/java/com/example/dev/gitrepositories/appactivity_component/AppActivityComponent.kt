package com.example.dev.gitrepositories.appactivity_component

import android.content.Context
import com.example.dev.gitrepositories.adapters.ViewPagerAdapter
import com.example.dev.gitrepositories.modules.AppModule
import com.example.dev.gitrepositories.modules.FragmentModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by dev on 12.11.16..
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, FragmentModule::class))
interface AppActivityComponent{

    fun inject(context: Context)

    fun presenter(): AppActivityPresenter

    fun adapter(): ViewPagerAdapter

}