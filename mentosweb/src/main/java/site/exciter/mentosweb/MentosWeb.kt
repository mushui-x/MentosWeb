package site.exciter.mentosweb

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import site.exciter.mentosweb.constant.MENTOS_WEB_ENABLE_ACTION_BAR
import site.exciter.mentosweb.constant.MENTOS_WEB_ENABLE_NATIVE_REFRESH
import site.exciter.mentosweb.constant.MENTOS_WEB_TITLE
import site.exciter.mentosweb.constant.MENTOS_WEB_URL
import site.exciter.mentosweb.ui.MentosWebViewActivity
import site.exciter.mentosweb.ui.MentosWebViewFragment

object MentosWeb {

    fun startWebActivity(
        context: Context,
        url: String,
        title: String,
        enableActionBar: Boolean = true,
        enableNativeRefresh: Boolean = true
    ) {
        val intent = Intent(context, MentosWebViewActivity::class.java).apply {
            putExtra(MENTOS_WEB_URL, url)
            putExtra(MENTOS_WEB_TITLE, title)
            putExtra(MENTOS_WEB_ENABLE_ACTION_BAR, enableActionBar)
            putExtra(MENTOS_WEB_ENABLE_NATIVE_REFRESH, enableNativeRefresh)
        }
        context.startActivity(intent)
    }

    fun getWebFragment(url: String, enableNativeRefresh: Boolean): Fragment {
        return MentosWebViewFragment.newInstance(url, enableNativeRefresh)
    }

}