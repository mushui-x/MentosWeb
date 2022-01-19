package site.exciter.mentosweb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_mentos_webview.*
import site.exciter.mentosweb.R
import site.exciter.mentosweb.constant.MENTOS_WEB_ENABLE_NATIVE_REFRESH
import site.exciter.mentosweb.constant.MENTOS_WEB_URL
import site.exciter.mentosweb.webview.MWebViewCallback

class MentosWebViewFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, MWebViewCallback {

    companion object {
        fun newInstance(url: String, enableNativeRefresh: Boolean): Fragment {
            val fragment = MentosWebViewFragment()
            val bundle = Bundle().apply {
                putString(MENTOS_WEB_URL, url)
                putBoolean(MENTOS_WEB_ENABLE_NATIVE_REFRESH, enableNativeRefresh)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var mUrl: String
    private var mEnableNativeRefresh = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        mUrl = bundle?.getString(MENTOS_WEB_URL) ?: ""
        mEnableNativeRefresh = bundle?.getBoolean(MENTOS_WEB_ENABLE_NATIVE_REFRESH, true) ?: true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mentos_webview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.apply {
            registerWebViewCallback(this@MentosWebViewFragment)
            loadUrl(mUrl)
        }
    }

    /**
     * 刷新
     */
    override fun onRefresh() {
        webView.reload()
    }

    override fun onPageStarted() {

    }

    override fun onPageFinished() {

    }

    override fun onError() {

    }

    override fun onUpdateTitle(title: String) {
        if (activity is MentosWebViewActivity) {
            (activity as MentosWebViewActivity).updateTitle(title)
        }
    }
}