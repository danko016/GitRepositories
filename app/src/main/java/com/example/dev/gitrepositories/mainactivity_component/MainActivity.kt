package com.example.dev.gitrepositories.mainactivity_component

import android.content.Intent
import android.os.Bundle
import com.example.dev.gitrepositories.R
import com.example.dev.gitrepositories.appactivity_component.AppActivity
import com.example.dev.gitrepositories.details_activity_component.DetailsActivity
import com.example.dev.gitrepositories.models.Repository
import com.example.dev.gitrepositories.modules.AppModule
import com.example.dev.gitrepositories.repository_component.RepositoryFragment
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.mcxiaoke.koi.ext.onClick
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity
@Inject
constructor() : MvpActivity<MainActivityView, MainActivityPresenter>(), MainActivityView {

    lateinit var component: MainActivityComponent

    private fun injectDependencies() {
        component = DaggerMainActivityComponent.builder()
                .appModule(AppModule(applicationContext))
                .build()
    }

    override fun createPresenter(): MainActivityPresenter {
        return component.presenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(applicationContext, AppActivity::class.java)

        btnEnter.onClick {
            startActivity(intent)
        }
    }
}
