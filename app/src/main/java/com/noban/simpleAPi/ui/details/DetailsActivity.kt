package com.noban.simpleAPi.ui.details

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.noban.simpleAPi.R
import com.noban.simpleAPi.core.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity: BaseActivity() ,DetailsView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        Log.e("Details","called")
        Picasso.get().load(intent.getStringExtra("URL")).into(iv_image_details)
        tv_title.text = intent.getStringExtra("TITLE")
    }
}