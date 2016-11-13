package com.example.dev.gitrepositories.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import javax.inject.Inject

/**
 * Created by dev on 12.11.16..
 */
class ViewPagerAdapter
@Inject
constructor(fm : FragmentManager,val listFragment: MutableList<Fragment>): FragmentStatePagerAdapter(fm){


    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getCount(): Int {
        return listFragment.size
    }

}