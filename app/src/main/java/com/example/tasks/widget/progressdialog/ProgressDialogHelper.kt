package com.example.tasks.widget.progressdialog

import android.app.ProgressDialog
import android.content.Context
import com.example.tasks.R

class ProgressDialogHelper {

    private var progressDialog: ProgressDialog? = null

    fun show(context: Context?, messageResourceId: String?) {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
        }
        val style: Int = R.style.dialogTransparentTheme
        progressDialog = ProgressDialog(context, style)
        progressDialog!!.setContentView(R.layout.progress_dialog)
        progressDialog!!.setMessage(messageResourceId)
        progressDialog!!.isIndeterminate = false
        progressDialog!!.setCancelable(false)
        progressDialog!!.setCanceledOnTouchOutside(false)
        progressDialog!!.show()
    }

    fun dismiss() {
        progressDialog?.dismiss()
        progressDialog = null
    }

}