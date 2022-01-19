package site.exciter.mentosweb.mainprocess

import com.google.gson.Gson
import site.exciter.mentosweb.ICallbackFromMainprocessToWebViewProcessInterface
import site.exciter.mentosweb.IWebViewProcessToMainProcessInterface
import site.exciter.mentosweb.command.Command
import java.util.*
import kotlin.collections.HashMap

/**
 *
 * @Description: 主进程命令管理器
 * @Author: ZhangJie
 * @CreateDate: 2022/1/19 11:24 上午
 */
object MainProcessCommandManager : IWebViewProcessToMainProcessInterface.Stub() {

    /**
     * 存放所有的命令
     */
    private val mCommands: HashMap<String, Command> = HashMap()

    init {
        //将所有实现Command接口的服务都load出来，并装载到mCommands中
        val serviceLoader = ServiceLoader.load(Command::class.java)
        for (command in serviceLoader) {
            if (!mCommands.containsKey(command.name())) {
                mCommands[command.name()] = command
            }
        }
    }

    /**
     * 处理js传来的命令
     */
    override fun handleWebCommand(
        commandName: String?,
        jsonParams: String?,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    ) {
        //执行命令
        mCommands[commandName]?.execute(
            Gson().fromJson(jsonParams, MutableMap::class.java),
            callback
        )
    }
}