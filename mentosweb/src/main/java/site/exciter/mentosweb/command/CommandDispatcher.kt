package site.exciter.mentosweb.command

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import site.exciter.mentosweb.ICallbackFromMainprocessToWebViewProcessInterface
import site.exciter.mentosweb.IWebViewProcessToMainProcessInterface
import site.exciter.mentosweb.MentosWeb
import site.exciter.mentosweb.constant.MENTOS_WEB_LOG_TAG
import site.exciter.mentosweb.mainprocess.MainProcessCommandService
import site.exciter.mentosweb.webview.MentosWebView

/**
 *
 * @Description: 命令分发
 * @Author: ZhangJie
 * @CreateDate: 2022/1/19 11:34 上午
 */
object CommandDispatcher : ServiceConnection {

    private var iWebViewProcessToMainProcessInterface: IWebViewProcessToMainProcessInterface? = null

    /**
     * 主进程绑定服务
     */
    fun initAidlConnection() {
        val intent = Intent(MentosWeb.application, MainProcessCommandService::class.java)
        MentosWeb.application?.bindService(intent, this, Context.BIND_AUTO_CREATE) ?: run {
            Log.e(MENTOS_WEB_LOG_TAG, "initAidlConnection: 请先在主进程Application中初始化MentosWeb")
        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        iWebViewProcessToMainProcessInterface =
            IWebViewProcessToMainProcessInterface.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        iWebViewProcessToMainProcessInterface = null
        initAidlConnection()
    }

    fun executeCommand(name: String, parameters: String, webView: MentosWebView) {
        iWebViewProcessToMainProcessInterface?.handleWebCommand(
            name, parameters, object : ICallbackFromMainprocessToWebViewProcessInterface.Stub() {
                override fun onResult(callbacId: String?, response: String?) {
                    webView.handleCallback(callbacId, response)
                }
            }
        )
    }
}