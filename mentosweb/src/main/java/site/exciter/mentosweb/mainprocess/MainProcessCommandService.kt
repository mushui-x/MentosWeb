package site.exciter.mentosweb.mainprocess

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 *
 * @Description: 主进程命令处理服务
 * @Author: ZhangJie
 * @CreateDate: 2022/1/19 11:32 上午
 */
class MainProcessCommandService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return MainProcessCommandManager.asBinder()
    }
}