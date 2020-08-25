package com.example.dev.gitrepositories.user_details

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import javax.inject.Inject

/**
 * Created by dev on 13.11.16..
 */
class UserPresenter
@Inject
constructor() : MvpBasePresenter<UserView>() {

    override fun attachView(view: UserView?) {
        super.attachView(view)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
    }

}