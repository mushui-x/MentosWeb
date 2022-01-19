package site.exciter.mentosweb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            MentosWeb.startWebActivity(this, "https://www.baidu.com", "WebView")
        }
        button2.setOnClickListener {
            MentosWeb.startWebActivity(this, "file:///android_asset/demo.html", "WebView")
        }
    }
}