package site.exciter.mentosweb.command

import site.exciter.mentosweb.ICallbackFromMainprocessToWebViewProcessInterface

/**
 *
 * @Description: Command
 * @Author: ZhangJie
 * @CreateDate: 2022/1/19 11:18 上午
 */
interface Command {
    fun name(): String
    fun execute(
        parameters: Map<*, *>,
        callback: ICallbackFromMainprocessToWebViewProcessInterface?
    )
}