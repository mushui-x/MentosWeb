package site.exciter.mentosweb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mentos_webview.*
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
        ivBack.setOnClickListener { finish() }
    }

    private fun initFragment() {
        val fragment = MentosWebViewFragment.newInstance(mUrl, mEnableNativeRefresh)
        val fragmentManger = supportFragmentManager
        val transaction = fragmentManger.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment).commit()
    }

    fun updateTitle(title: String) {
        tvTitle.text = title
    }

}