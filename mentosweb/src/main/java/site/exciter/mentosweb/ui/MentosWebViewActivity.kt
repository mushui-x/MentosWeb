package site.exciter.mentosweb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_mentos_webview.*
import site.exciter.mentosweb.MentosWeb
import site.exciter.mentosweb.R
import site.exciter.mentosweb.constant.MENTOS_WEB_ENABLE_ACTION_BAR
import site.exciter.mentosweb.constant.MENTOS_WEB_ENABLE_NATIVE_REFRESH
import site.exciter.mentosweb.constant.MENTOS_WEB_TITLE
import site.exciter.mentosweb.constant.MENTOS_WEB_URL

class MentosWebViewActivity : AppCompatActivity() {

    private lateinit var mUrl: String
    private lateinit var mTitle: String
    private var mEnableActionBar = true
    private var mEnableNativeRefresh = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentos_webview)

        initArguments()
        initActionBar()
        initFragment()
    }

    private fun initArguments() {
        mUrl = intent.getStringExtra(MENTOS_WEB_URL) ?: ""
        mTitle = intent.getStringExtra(MENTOS_WEB_TITLE) ?: ""
        mEnableActionBar = intent.getBooleanExtra(MENTOS_WEB_ENABLE_ACTION_BAR, true)
        mEnableNativeRefresh = intent.getBooleanExtra(MENTOS_WEB_ENABLE_NATIVE_REFRESH, true)
    }

    private fun initActionBar() {
        action_bar.visibility = if (mEnableActionBar) View.VISIBLE else View.GONE
        iv_back.setOnClickListener { finish() }
    }

    private fun initFragment() {
        val fragment = MentosWeb.getWebFragment(mUrl, mEnableNativeRefresh)
        val fragmentManger = supportFragmentManager
        val transaction = fragmentManger.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment).commit()
    }

    fun updateTitle(title: String) {
        tv_title.text = title
    }

}