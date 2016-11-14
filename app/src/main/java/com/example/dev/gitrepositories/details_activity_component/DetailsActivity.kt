package com.example.dev.gitrepositories.details_activity_component

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.dev.gitrepositories.R
import com.example.dev.gitrepositories.models.Repository
import com.example.dev.gitrepositories.modules.AppModule
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_details_layout.*
import kotlinx.android.synthetic.main.view_error.*
import kotlinx.android.synthetic.main.view_loading.*
import javax.inject.Inject



class DetailsActivity
@Inject
constructor() : MvpActivity<DetailsView, DetailsPresenter>(),  DetailsView {

    lateinit var component: DetailsComponent

    fun injectDependencies(){
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

        TVOwner.text = data?.owner?.login
        TVId.text = data?.owner?.id.toString()
        TVType.text = data?.owner?.type
        TVUserHtml.text = data?.owner?.htmlUrl

        val url = data?.owner?.avatarUrl
        Glide.with(applicationContext).load(url).into(IVOwnerAuthor)
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
        val repository: Repository = intent.extras.get("repository") as Repository
        setData(repository)

        TVHtml.onClick {
            val uri = Uri.parse(TVHtml.text.toString())
            val intent = Intent()
            intent.data = uri
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            applicationContext.startActivity(intent)
        }
        showContent()
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
