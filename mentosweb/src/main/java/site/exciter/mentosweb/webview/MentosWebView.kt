package site.exciter.mentosweb.webview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import site.exciter.mentosweb.constant.MENTOS_WEB_LOG_TAG

class MentosWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet, defStyleAttr: Int
) : WebView(context, attrs, defStyleAttr) {

    init {
        //配置设置
        MWebViewSettings.initSettings(this)
        addJavascriptInterface(this, "MentosJsBridge")
    }

    @JavascriptInterface
    fun invokeNativeAction(jsParams: String) {
        Log.d(MENTOS_WEB_LOG_TAG, "invokeNativeAction:$jsParams")
    }

    fun registerWebViewCallback(callback: MWebViewCallback) {
        webViewClient = MWebViewClient(callback)
        webChromeClient = MWebChromeClient(callback)
    }

}