package com.example.dev.gitrepositories.mainactivity_component

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import javax.inject.Inject

/**
 * Created by dev on 12.11.16..
 */
class MainActivityPresenter
@Inject
constructor() : MvpBasePresenter<MainActivityView>() {

    override fun attachView(view: MainActivityView?) {
        super.attachView(view)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
    }

}