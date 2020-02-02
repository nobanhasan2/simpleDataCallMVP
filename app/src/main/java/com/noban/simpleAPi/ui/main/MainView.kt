package com.noban.simpleAPi.ui.main

import com.noban.simpleAPi.model.RPData

interface MainView {

    fun loadDataInUI(rpData: List<RPData>)
}