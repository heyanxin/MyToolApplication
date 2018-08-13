package newland.com.mytoolapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import newland.com.mytoolapplication.utils.CrashHandlerUtil;
import newland.com.mytoolapplication.utils.LogUtil;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    public static Context mContext;
    private RefWatcher mRefWatcher;
    private static Map<String,Activity> mDestoryActivityMap = new HashMap<String, Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        setupLeakCanary();
        mContext = getApplicationContext();
        CrashHandlerUtil.getmInstance().init(mContext);
    }

    public static Context getInstance() {
        return mContext;
    }

    private RefWatcher setupLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

    public static void addDestoryActivity(String activityName, Activity activity) {
        mDestoryActivityMap.put(activityName, activity);
    }

    public static void destoryActivity(String activityName) {
        Set<String> keySet = mDestoryActivityMap.keySet();
        for (String key:keySet){
            LogUtil.d(TAG, "VideoActivity");
            mDestoryActivityMap.get(key).finish();
        }
    }
}
