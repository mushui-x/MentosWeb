package site.exciter.mentosweb.webview

import android.content.Context
import android.webkit.WebSettings
import android.webkit.WebView

object MWebViewSettings {

    fun initSettings(webView: WebView) {

        WebView.enableSlowWholeDocumentDraw()
        WebView.setWebContentsDebuggingEnabled(true)

        webView.settings.apply {
            cacheMode = WebSettings.LOAD_DEFAULT
            javaScriptEnabled = true
            mixedContentMode - WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            builtInZoomControls = false
            textZoom = 100
            databaseEnabled = true
            loadsImagesAutomatically = true
            blockNetworkImage = false
            allowFileAccess = true
            allowFileAccessFromFileURLs = false
            allowUniversalAccessFromFileURLs = false
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
            savePassword = false
            saveFormData = false
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
            defaultTextEncodingName = "utf-8"
            defaultFontSize = 16
            minimumFontSize = 10

            val appCacheDir = webView.context.getDir("cache", Context.MODE_PRIVATE).path
            databasePath = appCacheDir
            setAppCachePath(appCacheDir)
            setAppCacheMaxSize(80 * 1024 * 1024)

            setGeolocationEnabled(true)
            setNeedInitialFocus(true)
            setSupportZoom(true)
            setSupportMultipleWindows(false)
            setAppCacheEnabled(true)
        }

    }

}