package newland.com.mytoolapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import newland.com.mytoolapplication.utils.LogUtil;

public class CustomView extends View {

    private final static String TAG = "CustomView";
    private int mLastX;
    private int mLastY;
    private Scroller mScroller;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*@Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()) {
            ((View)getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX, int destY) {
        int scroolX = getScrollX();
        int delta = destX - scroolX;
        mScroller.startScroll(scroolX, 0, delta, 0, 2000);
        invalidate();
    }*/

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d(TAG, "MotionEvent: " + event.getAction());
        int x = (int) event.getX();
        if (x < 0) {
            x = 0;
        }
        LogUtil.d(TAG, "x: " + x);
        int y = (int) event.getY();
        if (y < 0) {
            y = 0;
        }
        LogUtil.d(TAG, "y: " + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offSetX = x - mLastX;
                int offSetY = y - mLastY;
                //1.layout()滑动
                //layout(getLeft() + offSetX, getTop() + offSetY, getRight() + offSetX, getBottom() + offSetY);

                //2.offsetLeftAndRight()和offsetTopAndBottom()滑动
                //offsetLeftAndRight(offSetX);
                //offsetTopAndBottom(offSetY);

                //3.LayoutParams滑动
                *//*ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();
                params.leftMargin = getLeft() + offSetX;
                params.topMargin = getTop() + offSetY;
                params.rightMargin = getRight() + offSetX;
                params.bottomMargin = getBottom() + offSetY;
                setLayoutParams(params);*//*

                //6.scollTo与scollBy滑动
                ((View)getParent()).scrollBy(-offSetX,-offSetY);

                //7.Scroller滑动
                break;
        }
        return  true;
    }*/
}
