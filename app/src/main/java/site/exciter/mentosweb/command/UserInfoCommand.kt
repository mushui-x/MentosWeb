package site.exciter.mentosweb.command

import android.content.Intent
import com.google.auto.service.AutoService
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import site.exciter.mentosweb.GlobalApplication
import site.exciter.mentosweb.ICallbackFromMainprocessToWebViewProcessInterface
import site.exciter.mentosweb.MessageEvent
import site.exciter.mentosweb.UserInfoActivity

/**
 *
 * @Description: 模拟获取用户信息返回给js
 * @Author: ZhangJie
 * @CreateDate: 2022/1/19 1:30 下午
 */
@AutoService(Command::class)
class UserInfoCommand : Command {

    private var callbackId: String? = null
    private var callback: ICallbackFromMainprocessToWebViewProcessInterface? = null

    override fun name(): String {
        return "getUserInfo"
    }

    override fun execute(
        parameters: Map<*, *>,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        this.callbackId = parameters["callbackId"].toString()
        this.callback = callback

        val intent = Intent(GlobalApplication.mApplication, UserInfoActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        GlobalApplication.mApplication?.startActivity(intent)
    }

    init {
        EventBus.getDefault().register(this)
    }

    @Subscribe
    fun onMessageEvent(event: MessageEvent) {
        callback?.onResult(callbackId, "{\"username\":\"${event.message}\"}")
    }
}