package site.exciter.mentosweb.webview

interface MWebViewCallback {
    fun onPageStarted()
    fun onPageFinished()
    fun onError()
    fun onUpdateTitle(title: String)
}