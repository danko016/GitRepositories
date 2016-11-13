package com.example.dev.gitrepositories.modules

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.dev.gitrepositories.repository_component.RepositoryFragment
import dagger.Module
import dagger.Provides

/**
 * Created by dev on 12.11.16..
 */
@Module
class FragmentModule (val fragmentManager: FragmentManager){

    @Provides
    fun providesFragmentManager(): FragmentManager{
        return fragmentManager
    }

    @Provides
    fun providesListFragment(): List<Fragment>{
        val list: List<Fragment> = listOf(RepositoryFragment())
        return list
    }
}