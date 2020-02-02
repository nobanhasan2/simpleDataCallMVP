package com.noban.simpleAPi.ui.splash


import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler

import com.noban.simpleAPi.ui.main.MainActivity
import com.noban.simpleAPi.R
import com.noban.simpleAPi.core.base.BaseActivity


internal class SplashActivity : BaseActivity(), SplashView {


    var SPLASH_TIME = 0

    // @Inject
    var mPresenter: SplashPresenter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mPresenter = SplashPresenter(this)
        mPresenter!!.startUI()

    }

    override fun navigateToHome() {
        startActivity(MainActivity::class.java, true)
        showForwardTransition(this)
    }

    override fun startUI() {
        goToNextPage()
    }

    fun goToNextPage() {
        Handler().postDelayed({
            object : CountDownTimer(2000, 2000) {

                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {

                    mPresenter!!.navToHome()

                    overridePendingTransition(
                        R.anim.anim_grow_from_center,
                        R.anim.anim_shrink_to_center
                    )
                    finish()
                }
            }.start()
        }, SPLASH_TIME.toLong())
    }

    companion object {

        val TAG = SplashActivity::class.java.simpleName
    }
}
