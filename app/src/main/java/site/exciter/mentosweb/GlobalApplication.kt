package site.exciter.mentosweb

import android.app.Application
import android.graphics.Color

/**
 *
 * @Description: GlobalApplication
 * @Author: ZhangJie
 * @CreateDate: 2022/1/19 12:43 下午
 */
class GlobalApplication : Application() {

    companion object {
        var mApplication: Application? = null
    }

    override fun onCreate() {
        super.onCreate()
        mApplication = this
        MentosWeb.init(this)
            .setRefreshSchemeColors(Color.BLUE)
    }
}