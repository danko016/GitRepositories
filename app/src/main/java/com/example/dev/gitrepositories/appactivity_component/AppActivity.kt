package com.example.dev.gitrepositories.appactivity_component

import android.os.Bundle
import com.example.dev.gitrepositories.R
import com.example.dev.gitrepositories.adapters.ViewPagerAdapter
import com.example.dev.gitrepositories.modules.AppModule
import com.example.dev.gitrepositories.modules.FragmentModule
import com.hannesdorfmann.mosby.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_app.*
import javax.inject.Inject

class AppActivity
@Inject
constructor() : MvpActivity<AppActivityView, AppActivityPresenter>(), AppActivityView {

    lateinit var component: AppActivityComponent
    var adapter: ViewPagerAdapter? = null

    private fun injectDependencies() {
        component = DaggerAppActivityComponent.builder()
                .appModule(AppModule(applicationContext))
                .fragmentModule(FragmentModule(supportFragmentManager))
                .build()
    }

    override fun createPresenter(): AppActivityPresenter {
        return component.presenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        adapter = component.adapter()
        vpPager.adapter = adapter
    }
}
