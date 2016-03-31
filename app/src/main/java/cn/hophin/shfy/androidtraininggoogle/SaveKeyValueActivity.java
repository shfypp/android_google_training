package cn.hophin.shfy.androidtraininggoogle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SaveKeyValueActivity extends AppCompatActivity {
    private static final String TEXT_SIZE = "textSize";
    TextView userNameTv;
    EditText mTextSize;
    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_key_value);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userNameTv = (TextView) findViewById(R.id.user_name_tv);
        mTextSize = (EditText) findViewById(R.id.text_size_tv_save_value);

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.shared_pres), Context.MODE_PRIVATE);
        userNameTv.setText(sharedPreferences.getString(getResources().getString(R.string.user_name_key), ""));

        mPreferences = getPreferences(Context.MODE_PRIVATE);



    }

    /**
     * update textSize and text
     * @param aFloat
     */
    private void upTextSize(float aFloat) {
        mTextSize.setText(String.valueOf(aFloat));
        mTextSize.setTextSize(aFloat);
    }

    /**
     * change text size and save info to preferences
     * @param view
     */
    public void changeTextSize(View view) {

        float tSize=Float.parseFloat(mTextSize.getText().toString());
        upTextSize(tSize);

        SharedPreferences.Editor editor=mPreferences.edit();
        editor.putFloat(TEXT_SIZE,tSize);
        editor.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        upTextSize(mPreferences.getFloat(TEXT_SIZE, 24));

    }
}
