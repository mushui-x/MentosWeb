package site.exciter.mentosweb.webview

import android.webkit.WebChromeClient
import android.webkit.WebView

class MWebChromeClient(private val callback: MWebViewCallback) : WebChromeClient() {

    override fun onReceivedTitle(view: WebView?, title: String?) {
        title?.let { callback.onUpdateTitle(title) }
    }
}