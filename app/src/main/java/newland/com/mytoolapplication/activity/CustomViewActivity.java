package newland.com.mytoolapplication.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import newland.com.mytoolapplication.R;
import newland.com.mytoolapplication.view.CustomView;

public class CustomViewActivity extends Activity implements View.OnClickListener{

    private final static String TAG = "CustomViewActivity";
    private Button mTestBtn;
    private CustomView mCustomView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        initView();
        initEvent();
    }

    private void initView() {
        mCustomView = (CustomView) findViewById(R.id.customview);
        mTestBtn = (Button) findViewById(R.id.test_btn);
        //4.View动画来移动
        //mCustomView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));

        //5.属性动画移动
       /* ObjectAnimator.ofFloat(mCustomView, "translationX", 0, 300).setDuration(1000).start();
        ObjectAnimator.ofFloat(mCustomView, "translationY", 0, 300).setDuration(1000).start();*/

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mCustomView, "translationX", 0.0f, 200.0f, 0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mCustomView, "scaleX", 1.0f, 2.0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mCustomView, "rotationX", 0.0f, 90.0f, 0.0F);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
        set.play(animator1).with(animator2).after(animator3);
        set.start();
    }

    private void initEvent() {
        mCustomView.setOnClickListener(this);
        mTestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_btn:
                //mCustomView.smoothScrollTo(-1000, 100);
                Toast.makeText(getApplicationContext(), "test_btn 被点击了", Toast.LENGTH_LONG).show();
                break;
            case R.id.customview:
                Toast.makeText(getApplicationContext(), "customview 被点击了", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
