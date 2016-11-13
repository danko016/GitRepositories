package com.example.dev.gitrepositories.appactivity_component

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import javax.inject.Inject

/**
 * Created by dev on 12.11.16..
 */
class AppActivityPresenter
@Inject
constructor() : MvpBasePresenter<AppActivityView>() {

    override fun attachView(view: AppActivityView?) {
        super.attachView(view)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
    }
}