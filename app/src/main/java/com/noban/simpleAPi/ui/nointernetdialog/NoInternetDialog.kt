package com.noban.simpleAPi.ui.nointernetdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.noban.simpleAPi.R


/**
 * Created by Rafiqul Hasan Rony on 1/29/19.
 * Audacity It Solution.
 */

class NoInternetDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setStyle(STYLE_NO_TITLE, R.style.FullScreenDialogTheme_Dark);
        this.isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.no_internet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //        TextView title = view.findViewById(R.id.tv_empty_text_title);
        //        TextView description = view.findViewById(R.id.tv_empty_text_description);
        //        title.setText(getString(R.string.no_internet));
        //        description.setText(getString(R.string.please_check_internet));
    }

    companion object {
        val TAG = NoInternetDialog::class.java.simpleName
    }

}
