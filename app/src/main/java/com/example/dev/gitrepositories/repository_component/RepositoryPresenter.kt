package com.example.dev.gitrepositories.repository_component

import android.util.Log
import android.view.View
import com.example.dev.gitrepositories.models.Repository
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by dev on 12.11.16..
 */
class RepositoryPresenter
@Inject
constructor() : MvpBasePresenter<RepositoryView>() {

    private val interactor = RepositoryInteractor()
    var repos: MutableList<Repository> = ArrayList()

    fun defaultView() {
        view?.loadDefaultState()
    }

    @Subscribe
    fun onEvent(event: RepositoryAdapter.EventOpenRepository){
        view?.openDetailsActivity(event.position)
    }

    @Subscribe
    fun onEvent(event: RepositoryAdapter.EventOpenUser){
        view?.openUserDetail(event.position)
    }

    fun loadRepositories(pullToRefresh: Boolean) {
        view?.getRepositoryName()?.let { repoName ->
            interactor.getRepositoriesAsync(repoName, 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repositories ->
                    if (repositories.getRepositories() != null) {
                        this.repos.clear()
                        this.repos.addAll(repositories.getRepositories()!!)
                        view?.setData(repositories.getRepositories())
                        view?.showContent()
                    }
                }, { t -> view?.showError(t, pullToRefresh) })
        }

    }

    override fun attachView(view: RepositoryView?) {
        super.attachView(view)
        EventBus.getDefault().register(this)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        EventBus.getDefault().unregister(this)
    }
}