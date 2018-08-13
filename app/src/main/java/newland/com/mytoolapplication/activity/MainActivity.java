package newland.com.mytoolapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.leakcanary.RefWatcher;

import newland.com.mytoolapplication.MyApplication;
import newland.com.mytoolapplication.R;
import newland.com.mytoolapplication.utils.LeakSingle;
import newland.com.mytoolapplication.utils.LogUtil;
import newland.com.mytoolapplication.view.MyCustomDialog;

public class MainActivity extends Activity implements View.OnClickListener{

    private final static String TAG = "";

    private Button mDisplayBtn;
    private Button mRecyclerviewBtn;
    private Button mCustomviewBtn;
    private Button mDanmuBtn;
    private Button mMyCustomDialogBtn;
    private TextView mLeakText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        /*LeakThread thread = new LeakThread();
        thread.start();*/
    }

    private void initView() {
        mDisplayBtn = (Button) findViewById(R.id.display_btn);
        mRecyclerviewBtn = (Button) findViewById(R.id.recyclerview_btn);
        mCustomviewBtn = (Button) findViewById(R.id.customview_btn);
        mDanmuBtn = (Button) findViewById(R.id.danmu_btn);
        mMyCustomDialogBtn = (Button) findViewById(R.id.custom_dialog_btn);
        mLeakText = (TextView) findViewById(R.id.leak_text);
    }

    private void initEvent() {
        mDisplayBtn.setOnClickListener(this);
        mRecyclerviewBtn.setOnClickListener(this);
        mCustomviewBtn.setOnClickListener(this);
        mDanmuBtn.setOnClickListener(this);
        mMyCustomDialogBtn.setOnClickListener(this);
        //LeakSingle.getInstance(this).setRetainedTextView(mLeakText);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.display_btn:
                intent = new Intent(MainActivity.this, DisplayMetricsActivity.class);
                startActivity(intent);
                break;
            case R.id.recyclerview_btn:
                intent = new Intent(MainActivity.this, RecyclerviewActivity.class);
                startActivity(intent);
                break;
            case R.id.customview_btn:
                intent = new Intent(MainActivity.this, CustomViewActivity.class);
                startActivity(intent);
                break;
            case R.id.danmu_btn:
                intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
                break;
            case R.id.custom_dialog_btn:
                intent = new Intent(MainActivity.this, MyCustomDialogActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }

    class LeakThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(6*60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
