package com.example.tasks.ext.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.text.format.Formatter
import android.util.Base64
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.example.tasks.R
import com.example.tasks.app.data.layout.UIDialogModel
import com.example.tasks.app.domain.subscribers.FailureData
import com.example.tasks.ext.constant.Const
import com.example.tasks.ext.constant.ConstFile
import com.example.tasks.ext.constant.NetworkCodes
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.io.FileOutputStream
import java.io.OutputStream

inline fun <T> T.applyIf(predicate: Boolean, block: T.() -> Unit): T = apply {
    if (predicate) block(this)
}

fun launchDelayedFunction(timeMillis: Long = 500, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ action() }, timeMillis)
}

fun forceClose() {
    android.os.Process.killProcess(android.os.Process.myPid())
}

fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

@SuppressLint("HardwareIds")
fun getDeviceId(ctx: Context): String {
    return Settings.Secure.getString(
        ctx.applicationContext.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}

@SuppressLint("HardwareIds")
fun getIpAddress(mContext: Context): String {
    val wm = mContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    return Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
}

fun getDeviceName(): String {
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL
    return if (model.startsWith(manufacturer)) {
        model.uppercase()
    } else manufacturer.uppercase() + " " + model
}

fun showMessageErrorConnection(view: View) {
    val snackBar = Snackbar.make(view, "Connection error occurred", Snackbar.LENGTH_INDEFINITE)
    snackBar.setAction("Please try again") { snackBar.dismiss() }
    snackBar.show()
}

fun checkPermission(
    context: Fragment, vararg permissions: String,
    resLauncher: ActivityResultLauncher<Any>
): Boolean {
    var allPermitted = false
    for (permission in permissions) {
        allPermitted = (ContextCompat.checkSelfPermission(context.requireContext(), permission)
                == PackageManager.PERMISSION_GRANTED)
        if (!allPermitted) break
    }
    if (allPermitted) return true
    resLauncher.launch(permissions)
    return false
}


fun getCurrentTimeZoneTime(): Long {
    // translate to Asia local time
    val time: DateTime = DateTime().withZone(DateTimeZone.forID("Asia/Jakarta"))
    return time.millis
}

fun getErrorDetail(failureData: FailureData): UIDialogModel {
    when (failureData.code) {
        NetworkCodes.NO_CONNECTION -> {
            return UIDialogModel(
                R.drawable.ic_error_no_internet, R.string.error_network_title,
                titleStr = "", R.string.error_network_description, descriptionStr = "",
                descriptionTwo = null, specialWarning = null, bgPositive = null,
                bgNegative = null, R.string.error_network_button, btnNegative = null
            )
        }
        NetworkCodes.CONNECTION_ERROR, NetworkCodes.TIMEOUT_ERROR, NetworkCodes.HTTP_CONFLICT -> {
            return UIDialogModel(
                R.drawable.ic_error_internet_interupted, R.string.error_network_interupted_title,
                titleStr = "", R.string.error_network_interupted_description, descriptionStr = "",
                descriptionTwo = null, specialWarning = null, bgPositive = null,
                bgNegative = null, R.string.error_network_button, btnNegative = null
            )
        }
        NetworkCodes.FORBIDDEN -> {
            return UIDialogModel(
                R.drawable.ic_error_403, R.string.error_forbidden_title,
                titleStr = "", R.string.error_forbidden_description, descriptionStr = "",
                descriptionTwo = null, specialWarning = null, bgPositive = null,
                bgNegative = null, R.string.error_forbidden_button, btnNegative = null
            )
        }
        NetworkCodes.GENERIC_ERROR -> {
            return UIDialogModel(
                R.drawable.ic_error_general, R.string.error_general_title,
                titleStr = "", R.string.error_general_description, descriptionStr = "",
                descriptionTwo = null, specialWarning = null, bgPositive = null,
                bgNegative = null, R.string.error_network_button, btnNegative = null
            )
        }
        else -> {
            return UIDialogModel(
                R.drawable.ic_error_general, title = null,
                titleStr = failureData.code.toString(), description = null,
                descriptionStr = failureData.message ?: "Pesan Error Kosong",
                descriptionTwo = null, specialWarning = null, bgPositive = null,
                bgNegative = null, R.string.error_network_button, btnNegative = null
            )
        }

    }
}

fun isAutomaticDate(activity: Activity, fragmentManager: FragmentManager) {
    if (Settings.Global.getInt(activity.contentResolver, Settings.Global.AUTO_TIME, 0) == 0
        && activity.javaClass.canonicalName != "SplashActivity"
    ) {
        val dialog = UIDialogModel(
            R.drawable.ic_error_automatic_time, R.string.automatic_time_title,
            titleStr = "", R.string.automatic_time_description, descriptionStr = "",
            descriptionTwo = null, specialWarning = null, bgPositive = null,
            bgNegative = null, R.string.automatic_time_button, btnNegative = null
        )

//        showDialogInfoBottom(
//            isCallback = false, isCancelable = false,
//            fragmentManager = fragmentManager, codeFromLayout = null,
//            reqTargetCode = null, resTargetCode = null, uiDialogModel = dialog
//        )

//        dialog.setOnButtonClicked {
//            startActivityForResult(
//                Intent(Settings.ACTION_DATE_SETTINGS),
//                BaseActivity.REQ_CODE_SETTINGS_DATE
//            )
//            dialog.dismissDialog()
//        }
    }
}

fun setTransparentNotificationBar(activity: AppCompatActivity) {
    val window = activity.window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}

fun initOnBackPress(activity: FragmentActivity): Boolean {
    val result: Boolean
    val countStack = activity.supportFragmentManager.backStackEntryCount - 1
    result = if (countStack != 0) {
        false
    } else {
        activity.finish()
        true
    }
    return result
}

fun saveImage(mContext: Context, image: Bitmap, fileName: String) {
    val imageFile = ConstFile.getImage(mContext, fileName)
    try {
        val fOut: OutputStream = FileOutputStream(imageFile)
        image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
        fOut.close()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}


// Inject CSS method: read style.css from assets folder
// Append stylesheet to document head
fun injectCSS(context: Context, webView: WebView) {
    try {
        val inputStream = context.assets.open(Const.STYLE_CSS)
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()
        val encoded = Base64.encodeToString(buffer, Base64.NO_WRAP)
        webView.loadUrl(
            "javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +  // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()"
        )
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}


