package newland.com.mytoolapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import newland.com.mytoolapplication.utils.LogUtil;
import newland.com.mytoolapplication.view.MyCustomDialog;

public class MyCustomDialogActivity extends Activity{
    private final static String TAG = "MyCustomDialogActivity";

    private MyCustomDialog mMyCustomDialog = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
        showMyCustomDialog();
    }

    private void initView() {}

    private void initEvent() {}

    private void showMyCustomDialog() {
        LogUtil.d(TAG, "showMyCustomDialog");
        mMyCustomDialog = MyCustomDialog.Builder(this)
                .setTitle("对话框标题")
                .setMessage("对话框内容对话框内容对话框内容对话框内容对话框内容对话框内容对话框内容对话框内容")
                .setOnConfirmClickListener("确定", new MyCustomDialog.onConfirmClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MyCustomDialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                        if (mMyCustomDialog != null) {
                            mMyCustomDialog.dismiss();
                        }
                    }
                })
                .setOnCancelClickListener("取消", new MyCustomDialog.onCancelClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MyCustomDialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        if (mMyCustomDialog != null) {
                            mMyCustomDialog.dismiss();
                        }
                    }
                })
                .build()
                .shown();
    }
}
