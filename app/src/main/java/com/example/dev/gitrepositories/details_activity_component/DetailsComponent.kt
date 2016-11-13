package com.example.dev.gitrepositories.details_activity_component

import android.content.Context
import com.example.dev.gitrepositories.modules.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by dev on 13.11.16..
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface DetailsComponent {

    fun inject(context: Context)

    fun presenter(): DetailsPresenter

}