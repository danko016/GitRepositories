package com.example.dev.gitrepositories.user_details

import android.content.Context
import com.example.dev.gitrepositories.modules.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by dev on 13.11.16..
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface UserComponent {

    fun inject(context: Context)

    fun presenter(): UserPresenter

}