package site.exciter.mentosweb.webview

interface MWebViewCallback {
    fun onPageStarted()
    fun onPageFinished()
    fun onProgressChanged(progress: Int)
    fun onError()
    fun onUpdateTitle(title: String)
}