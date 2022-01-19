package site.exciter.mentosweb.webview

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.google.gson.Gson
import site.exciter.mentosweb.command.CommandDispatcher
import site.exciter.mentosweb.constant.MENTOS_WEB_LOG_TAG
import site.exciter.mentosweb.entity.JsParam

class MentosWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    init {
        CommandDispatcher.initAidlConnection()
        //配置设置
        MWebViewSettings.initSettings(this)
        addJavascriptInterface(this, "MentosJsBridge")
    }

    @JavascriptInterface
    fun invokeNativeAction(jsParams: String) {
        Log.d(MENTOS_WEB_LOG_TAG, "invokeNativeAction:$jsParams")
        if (!TextUtils.isEmpty(jsParams)) {
            val jsParamsObject = Gson().fromJson(jsParams, JsParam::class.java)
            if (jsParamsObject != null) {
                CommandDispatcher.executeCommand(
                    jsParamsObject.name, Gson().toJson(jsParamsObject.param), this
                )
            }
        }
    }

    fun registerWebViewCallback(callback: MWebViewCallback) {
        webViewClient = MWebViewClient(callback)
        webChromeClient = MWebChromeClient(callback)
    }

    fun handleCallback(callbackId: String?, response: String?) {
        if (!TextUtils.isEmpty(callbackId) && !TextUtils.isEmpty(response)) {
            post {
                kotlin.run {
                    //将结果回调给js
                    val jsCode = "javascript:mentosWeb.callback('$callbackId',$response)"
                    evaluateJavascript(jsCode, null)
                }
            }
        }
    }

}