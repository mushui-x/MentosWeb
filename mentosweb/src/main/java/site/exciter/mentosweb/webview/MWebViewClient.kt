package site.exciter.mentosweb.webview

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MWebViewClient(private val callback: MWebViewCallback) : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        callback.onPageStarted()
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        callback.onPageFinished()
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        callback.onError()
    }
}