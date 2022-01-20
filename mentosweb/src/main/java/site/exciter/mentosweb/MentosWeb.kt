package site.exciter.mentosweb

import android.app.Application
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

    var application: Application? = null

    fun init(application: Application): MentosWeb {
        this.application = application
        return this
    }

    /**
     * 设置进度条颜色
     * @param color 颜色值
     */
    fun setProgressBarColor(color: Int): MentosWeb {
        MentosWebConfig.PROGRESS_BAR_COLOR = color
        return this
    }

    /**
     * 设置刷新控件的颜色方案
     * @param colors 颜色方案，可设置多个颜色
     */
    fun setRefreshSchemeColors(vararg colors: Int): MentosWeb {
        MentosWebConfig.REFRESH_SCHEME_COLORS = colors
        return this
    }

    fun startWebActivity(
        context: Context,
        url: String,
        title: String = "",
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