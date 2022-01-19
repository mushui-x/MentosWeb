package site.exciter.mentosweb.command

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.auto.service.AutoService
import site.exciter.mentosweb.GlobalApplication
import site.exciter.mentosweb.ICallbackFromMainprocessToWebViewProcessInterface

/**
 *
 * @Description: Toast
 * @Author: ZhangJie
 * @CreateDate: 2022/1/19 12:52 下午
 */
@AutoService(Command::class)
class ShowToastCommand : Command {

    override fun name(): String {
        return "showToast"
    }

    override fun execute(
        parameters: Map<*, *>,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(
                GlobalApplication.mApplication,
                parameters["message"].toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}