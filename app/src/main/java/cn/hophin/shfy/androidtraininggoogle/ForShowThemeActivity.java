package cn.hophin.shfy.androidtraininggoogle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/3/28.
 */
public class ForShowThemeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_theme);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.show_theme);

        TextView textView = new TextView(this);
        textView.setTextSize(40);
//        textView.setText("透明背景的Activity");
        textView.setText("像对话框一样的Activity");

        relativeLayout.addView(textView);

    }
}
