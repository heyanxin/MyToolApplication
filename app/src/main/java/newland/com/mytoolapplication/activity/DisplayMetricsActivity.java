package newland.com.mytoolapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import newland.com.mytoolapplication.R;

public class DisplayMetricsActivity extends Activity{
    private TextView mPixelsWidthText;
    private TextView mPixelsHeightText;
    private TextView mXDpiText;
    private TextView mYDpiText;
    private TextView mDensityText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        initView();
        initData();
    }

    private void initView() {
        mPixelsWidthText = (TextView) findViewById(R.id.pixels_width_text);
        mPixelsHeightText = (TextView) findViewById(R.id.pixels_height_text);
        mXDpiText = (TextView) findViewById(R.id.xdpi_text);
        mYDpiText = (TextView) findViewById(R.id.ydpi_text);
        mDensityText = (TextView) findViewById(R.id.density_text);
    }

    private void initData() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        float density = dm.density;

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;

        mPixelsWidthText.setText(String.valueOf(screenWidth));
        mPixelsHeightText.setText(String.valueOf(screenHeight));
        mXDpiText.setText(String.valueOf(xdpi));
        mYDpiText.setText(String.valueOf(ydpi));
        mDensityText.setText(String.valueOf(density));
    }
}
