package com.noban.simpleAPi.core

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.util.Base64
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication


import com.noban.simpleAPi.R
import com.noban.simpleAPi.utility.ConnectivityReceiver
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

import org.jetbrains.annotations.Contract

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class App : MultiDexApplication() {

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
        }

    override fun onCreate() {
        super.onCreate()

        app = this


        val prettyFormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .methodCount(3)
            .methodOffset(1)
            .tag(getString(R.string.app_name))
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(prettyFormatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

        if (BuildConfig.DEBUG) {
            try {
                val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
                for (signature in info.signatures) {
                    val messageDigest = MessageDigest.getInstance("SHA")
                    messageDigest.update(signature.toByteArray())
                    Logger.i(
                        "KeyHash: " + Base64.encodeToString(
                            messageDigest.digest(),
                            Base64.DEFAULT
                        )
                    )
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

        }


    }

    fun setConnectivityListener(connectivityListener: ConnectivityReceiver.ConnectivityReceiverListener) {
        ConnectivityReceiver.connectivityReceiverListener = connectivityListener
    }

    companion object {

        @get:Contract(pure = true)
        @get:Synchronized
        lateinit var app: App

        fun getApplication(activity: Activity): App {
            return activity.application as App
        }
    }
}
