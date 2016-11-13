package com.example.dev.gitrepositories.details_activity_component

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import javax.inject.Inject

/**
 * Created by dev on 13.11.16..
 */
class DetailsPresenter
@Inject
constructor() : MvpBasePresenter<DetailsView>() {

    override fun attachView(view: DetailsView?) {
        super.attachView(view)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
    }

}