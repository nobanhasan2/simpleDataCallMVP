package com.noban.simpleAPi.ui.splash


/**
 * Created by Rafiqul Hasan Rony on 1/31/19.
 * Audacity It Solution.
 */

class SplashPresenter

internal constructor(
    private val mView: SplashView

)
     {
         fun navToHome() {
         mView.navigateToHome()
     }

     fun startUI() {
        mView.startUI()
    }
}
