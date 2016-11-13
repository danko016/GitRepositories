package com.example.dev.gitrepositories.repository_component

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dev.gitrepositories.R
import com.example.dev.gitrepositories.details_activity_component.DetailsActivity
import com.example.dev.gitrepositories.models.Repositories
import com.example.dev.gitrepositories.models.Repository
import com.example.dev.gitrepositories.modules.AppModule
import com.example.dev.gitrepositories.modules.LayoutModule
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment
import com.mcxiaoke.koi.ext.onClick
import com.mcxiaoke.koi.ext.onTextChange
import kotlinx.android.synthetic.main.repository_layout.*

/**
 * Created by dev on 12.11.16..
 */
class RepositoryFragment : MvpLceFragment<SwipeRefreshLayout, List<Repository>, RepositoryView, RepositoryPresenter>(), SwipeRefreshLayout.OnRefreshListener, RepositoryView {


    lateinit var component: RepositoryComponent
    var layoutManager: LinearLayoutManager? = null
    var adapter: RepositoryAdapter? = null

    fun injectDependencies() {
        component = DaggerRepositoryComponent.builder()
                .appModule(AppModule(context))
                .layoutModule(LayoutModule(context))
                .build()
        adapter = component.adapter()
        layoutManager = component.layout()


    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadRepositories(pullToRefresh)
    }

    override fun setData(data: List<Repository>) {
        loadMore(layoutManager!!)
        adapter?.repoList?.clear()
        adapter?.repoList?.addAll(data)
    }

    override fun addData(data: Repositories?) {
        adapter?.repoList?.addAll(data?.getRepositories()!!)
    }

    override fun createPresenter(): RepositoryPresenter {
        return component.presenter()
    }

    override fun getErrorMessage(e: Throwable?, pullToRefresh: Boolean): String {
        return e?.message.toString()
    }

    override fun onRefresh() {
        recyclerView.clearOnScrollListeners()
        loadData(false)
    }

    override fun loadDefaultState() {
        contentView.visibility = View.VISIBLE
        loadingView.visibility = View.GONE
        errorView.visibility = View.GONE
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injectDependencies()
        return inflater?.inflate(R.layout.repository_layout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.defaultView()
        loadEnteredData()

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        contentView.setOnRefreshListener(this)

        etSearch.onTextChange { charSequence, start, before, count -> presenter.search(charSequence) }
    }

    override fun openDetailsActivity(position: Int) {
        val repository: Repository = adapter?.repoList!![position]
        val intent: Intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra("repository",repository)
        context.startActivity(intent)
    }

    override fun loadEnteredData() {
        BTNSearch.onClick {
            loadData(true)
        }
    }

    override fun searchRepos(): String{
        val searchRepos = etSearchQ.text.toString()
        if (searchRepos == ("")){
            Toast.makeText(context, "Please enter text in repository label", Toast.LENGTH_SHORT).show()
        }
        return searchRepos
    }

    override fun showContent() {
        super.showContent()
        adapter?.notifyDataSetChanged()
        contentView.visibility = View.VISIBLE
        contentView.isRefreshing = false
    }

    override fun showError(e: Throwable, pullToRefresh: Boolean) {
        super.showError(e, pullToRefresh)
        errorView.visibility = View.VISIBLE
    }

    override fun showLoading(pullToRefresh: Boolean) {
        super.showLoading(pullToRefresh)
        loadingView.visibility = View.VISIBLE
        errorView.visibility = View.GONE
    }

    override fun loadMore(layout: LinearLayoutManager){
        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layout){
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                presenter.loadMoreRepositories(false)
            }

        })
    }


}


