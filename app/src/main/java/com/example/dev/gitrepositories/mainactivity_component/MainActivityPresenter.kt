package com.example.dev.gitrepositories.mainactivity_component

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import javax.inject.Inject

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