package com.noban.simpleAPi.core.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.noban.simpleAPi.R
import com.noban.simpleAPi.core.App
import com.noban.simpleAPi.ui.nointernetdialog.NoInternetDialog
import com.noban.simpleAPi.utility.ConnectivityReceiver

open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    protected var app: App? = null
    private val connectivityReceiver: ConnectivityReceiver? = null
    private var noInternetDialog: NoInternetDialog? = null
    protected var showNoInternetDialog = false

    internal var progressDialog: ProgressDialog? = null

    val activity: AppCompatActivity
        get() = this

    private val isLoaderShowing: Boolean
        get() = if (progressDialog == null) {
            false
        } else {
            progressDialog!!.isShowing
        }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            if (noInternetDialog != null) {
                if (noInternetDialog!!.dialog != null) {
                    if (noInternetDialog!!.dialog.isShowing) {
                        noInternetDialog!!.dismiss()
                    }
                }
            }
        } else {
            if (noInternetDialog == null) {
                noInternetDialog = NoInternetDialog()
                if (noInternetDialog!!.dialog != null) {
                    if (!noInternetDialog!!.dialog.isShowing) {
                        // noInternetDialog.show(getSupportFragmentManager(), NoInternetDialog.TAG);
                    }
                }
            } else {
                if (noInternetDialog!!.dialog != null) {
                    if (!noInternetDialog!!.dialog.isShowing) {
                        //noInternetDialog.show(getSupportFragmentManager(), NoInternetDialog.TAG);
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (showNoInternetDialog) {
            val intentFilter = IntentFilter()
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            registerReceiver(connectivityReceiver, intentFilter)
            app!!.setConnectivityListener(this)
        }
    }

    override fun onPause() {
        super.onPause()
        if (showNoInternetDialog) {
            unregisterReceiver(connectivityReceiver)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun showLoader() {
        runOnUiThread {
            if (!isLoaderShowing) {
                progressDialog = ProgressDialog.show(this, null, "Please wait", true)
            }
        }
    }

    fun hideLoader() {
        runOnUiThread {
            if (isLoaderShowing) {
                progressDialog!!.dismiss()
            }
        }
    }

    fun showToast(text: String) {
        runOnUiThread { Toast.makeText(this, text, Toast.LENGTH_SHORT).show() }
    }

    protected fun startActivityAndClearAll(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    protected fun showForwardTransition(activity: Activity) {
        try {
            activity.overridePendingTransition(
                R.anim.anim_slide_in_right,
                R.anim.anim_slide_out_left
            )
        } catch (e: Exception) {
            //  ShowLog.e("ActivityForwardTransi", e.getMessage());
        }

    }

    protected fun showBackwardTransition(activity: Activity) {
        try {
            activity.overridePendingTransition(
                R.anim.anim_slide_in_left,
                R.anim.anim_slide_out_right
            )
        } catch (e: Exception) {
            //   ShowLog.e("ActivityBackwardTransi", e.getMessage());
        }

    }

    protected fun startActivity(activity: Class<*>, finishSelf: Boolean) {
        val intent = Intent(this, activity)
        startActivity(intent)
        if (finishSelf) {
            finish()
        }
    }

    companion object {


        internal val SNACK_BAR_ERROR = 0
        internal val SNACK_BAR_SUCCESS = 1
        internal val SNACK_BAR_NORMAL = 2
    }

}
