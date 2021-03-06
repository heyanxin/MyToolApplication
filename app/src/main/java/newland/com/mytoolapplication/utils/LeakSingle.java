package newland.com.mytoolapplication.utils;

import android.content.Context;
import android.widget.TextView;

import newland.com.mytoolapplication.R;

/**
 * 泄露单例, 设计混乱, 单例只应该做事务性的工作, 页面操作应该使用回调.
 */
public class LeakSingle {
    private Context mContext;
    private TextView mTextView;

    private static LeakSingle sInstance;

    private LeakSingle(Context context) {
        mContext = context;
    }

    public static LeakSingle getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LeakSingle(context);
        }
        return sInstance;
    }

    // 内存泄露
    public void setRetainedTextView(TextView tv) {
        mTextView = tv;
        mTextView.setText(mContext.getString(R.string.app_name));
    }

    // 删除引用, 防止泄露
    public void removeRetainedTextView() {
        mTextView = null;
    }
}