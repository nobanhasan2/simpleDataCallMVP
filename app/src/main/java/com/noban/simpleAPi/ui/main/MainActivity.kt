package com.noban.simpleAPi.ui.main

import android.os.Bundle
import com.noban.simpleAPi.R
import com.noban.simpleAPi.core.api.Repository
import com.noban.simpleAPi.core.base.BaseActivity

class MainActivity : BaseActivity() ,MainView{


    private  lateinit var mPresenter : MainPresenter
    private lateinit var mRepository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRepository = Repository(this)
        mPresenter = MainPresenter(this,mRepository)
        mPresenter.getData()

    }
}
