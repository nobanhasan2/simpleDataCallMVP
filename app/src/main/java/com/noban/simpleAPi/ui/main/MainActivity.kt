package com.noban.simpleAPi.ui.main

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noban.simpleAPi.R
import com.noban.simpleAPi.core.api.Repository
import com.noban.simpleAPi.core.base.BaseActivity
import com.noban.simpleAPi.model.RPData
import com.noban.simpleAPi.ui.main.adapter.RecycleDataAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() ,MainView{


    private  lateinit var mPresenter : MainPresenter
    private lateinit var mRepository: Repository
    private lateinit var mAdapter: RecycleDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRepository = Repository(this)
        mPresenter = MainPresenter(this,mRepository)
        mPresenter.getData()



    }

    override fun loadDataInUI(rpData: List<RPData>) {
        mAdapter = RecycleDataAdapter(rpData)
        rv_photo.layoutManager = GridLayoutManager(this,2)
        rv_photo.adapter = mAdapter

    }
}
