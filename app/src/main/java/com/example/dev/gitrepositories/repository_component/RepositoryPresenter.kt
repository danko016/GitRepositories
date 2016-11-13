package com.example.dev.gitrepositories.repository_component

import android.util.Log
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

    val interactor = RepositoryInteractor()
    var nextPage: Int = 0
    var repos: MutableList<Repository> = ArrayList()

    fun defaultView() {
        view?.loadDefaultState()
    }

    @Subscribe
    fun onEvent(event: RepositoryAdapter.Event){
        view?.openDetailsActivity(event.position)
    }

    fun loadRepositories(pullToRefresh: Boolean) {

        interactor.getRepositoriesAsync(view?.searchRepos()!!, 0)
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

    fun loadMoreRepositories(pullToRefresh: Boolean) {
        nextPage++
        interactor.getRepositoriesAsync(view?.searchRepos()!!, nextPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repositories ->
                    view?.addData(repositories)
                    view?.showContent()
                }, { t -> view?.showError(t, pullToRefresh) })

    }

    fun search(char: CharSequence) {
        if (char.length == 0) {
            Log.d("tag", "list is not filtered")
            view?.setData(repos)
            view?.showContent()
        } else {

            Log.d("tag", "list is filtered")

            val temp: MutableList<Repository> = ArrayList()

            for (repo in repos) {
                if (repo.name?.contains(char, true)!!) {
                    temp.add(repo)
                }
            }

            view?.setData(temp)
            view?.showContent()
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