package com.example.dev.gitrepositories.modules

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides

/**
 * Created by dev on 12.11.16..
 */
@Module
class LayoutModule(val context: Context) {

    @Provides
    fun providesLinearLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(context)
    }
}