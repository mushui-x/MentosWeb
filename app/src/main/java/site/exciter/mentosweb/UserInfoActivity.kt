package site.exciter.mentosweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_user_info.*
import org.greenrobot.eventbus.EventBus

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        button.setOnClickListener {
            //模拟获取用户信息
            EventBus.getDefault().post(MessageEvent(USER_INFO_CODE, "---MentosWeb---"))
            finish()
        }
    }
}