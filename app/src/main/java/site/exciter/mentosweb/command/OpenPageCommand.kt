package site.exciter.mentosweb.command

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.google.auto.service.AutoService
import site.exciter.mentosweb.GlobalApplication
import site.exciter.mentosweb.ICallbackFromMainprocessToWebViewProcessInterface

/**
 *
 * @Description: 打开本地页面
 * @Author: ZhangJie
 * @CreateDate: 2022/1/19 1:15 下午
 */
@AutoService(Command::class)
class OpenPageCommand : Command {
    override fun name(): String {
        return "openPage"
    }

    override fun execute(
        parameters: Map<*, *>,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        val targetPage = parameters["targetPage"].toString()
        val intent = Intent().apply {
            component = ComponentName(GlobalApplication.mApplication as Context, targetPage)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        GlobalApplication.mApplication?.startActivity(intent)
    }
}