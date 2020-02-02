/*
 * Copyright (C) 2017 NURDCODER
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://nurdcoder.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.noban.simpleAPi.utility

import android.util.Log

import com.noban.simpleAPi.BuildConfig


/**
 * ****************************************************************************
 * * Copyright © 2018 NURDCODER, All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 10/25/2018
 * * Email : muktadir@nurdcoder.com
 * *
 * * Purpose :
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 10/25/2018.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 10/25/2018.
 * ****************************************************************************
 */

 object ShowLog {


    fun i(tag: String, string: String) {
        if (BuildConfig.DEBUG) Log.i(tag, string)
    }

    fun e(tag: String, string: String) {
        if (BuildConfig.DEBUG) Log.e(tag, string)
    }

    fun d(tag: String, string: String) {
        if (BuildConfig.DEBUG) Log.d(tag, string)
    }

    fun v(tag: String, string: String) {
        if (BuildConfig.DEBUG) Log.v(tag, string)
    }

    fun w(tag: String, string: String) {
        if (BuildConfig.DEBUG) Log.w(tag, string)
    }
}