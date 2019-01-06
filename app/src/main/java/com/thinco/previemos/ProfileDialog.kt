package com.thinco.previemos

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ProfileDialog(context: Context) : Dialog(context) {

    private var content: String? = null

    fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.profile_dialog, container, false)

        val btnClose = view.findViewById<View>(R.id.BtnCloseProfileDialog) as ImageView
        val btnLogOut = view.findViewById<View>(R.id.BtnLogOut) as Button

        val tvProfileName = view.findViewById<View>(R.id.tvUserName) as TextView
        tvProfileName.text = content

        //FontUtils.setTypeface(getActivity(), textViewQuestion, "fonts/mangal.ttf");
        //FontUtils.setTypeface(getActivity(), textViewAnswer, "fonts/mangal.ttf");
        btnClose.setOnClickListener {
            Toast.makeText(context, "Cerrando el dialog", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        btnLogOut.setOnClickListener {
            Snackbar.make(view,"Logout",Snackbar.LENGTH_LONG)
            dismiss()
        }
        return view
    }
}