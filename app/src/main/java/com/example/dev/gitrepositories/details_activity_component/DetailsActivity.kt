package com.example.dev.gitrepositories.details_activity_component

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.dev.gitrepositories.R
import com.example.dev.gitrepositories.models.Repository
import com.example.dev.gitrepositories.modules.AppModule
import com.example.dev.gitrepositories.repository_component.RepositoryAdapter
import com.example.dev.gitrepositories.user_details.UserActivity
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.repository_details.*
import kotlinx.android.synthetic.main.activity_details_layout.*
import kotlinx.android.synthetic.main.view_error.*
import kotlinx.android.synthetic.main.view_loading.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject



class DetailsActivity
@Inject
constructor() : MvpActivity<DetailsView, DetailsPresenter>(),  DetailsView {

    lateinit var component: DetailsComponent

    private var repository: Repository? = null

    private fun injectDependencies(){
        component = DaggerDetailsComponent.builder()
                .appModule(AppModule(applicationContext))
                .build()
    }

    override fun setData(data: Repository?) {
        TVDetailsName.text = data?.name
        TVHtml.text = data?.htmlUrl
        TVCreated.text = data?.createdAt
        TVUpdated.text = data?.updatedAt
        TVLanguage.text = data?.language
        TVBranch.text = data?.defaultBranch
        TVWiki.text = data?.hasWiki.toString()
    }

    override fun loadData(pullToRefresh: Boolean) {

    }

    override fun createPresenter(): DetailsPresenter {
        return component.presenter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_layout)
        repository = intent.extras.get("repository") as Repository
        setData(repository)

        TVHtml.onClick {
            val uri = Uri.parse(TVHtml.text.toString())
            val intent = Intent()
            intent.data = uri
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            applicationContext.startActivity(intent)
        }

        btnWeb.onClick {
            val uri = Uri.parse(TVHtml.text.toString())
            val intent = Intent()
            intent.data = uri
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            applicationContext.startActivity(intent)
        }

        btnOpenUserDetail.onClick {
            openUserDetail()
        }

        showContent()
    }

    private fun openUserDetail() {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("repository",repository)
        startActivity(intent)
    }

    override fun showContent() {
        contentView.visibility = View.VISIBLE
        loadingView.visibility = View.GONE
        errorView.visibility = View.GONE
    }

    override fun showError(e: Throwable?, pullToRefresh: Boolean) {
        errorView.visibility = View.VISIBLE
    }

    override fun showLoading(pullToRefresh: Boolean) {
        loadingView.visibility = View.VISIBLE
    }
}
