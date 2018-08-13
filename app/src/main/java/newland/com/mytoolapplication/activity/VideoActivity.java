package newland.com.mytoolapplication.activity;
import java.io.File;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.MediaController;
import android.widget.VideoView;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;
import newland.com.mytoolapplication.MyApplication;
import newland.com.mytoolapplication.R;
import newland.com.mytoolapplication.utils.LogUtil;

public class VideoActivity extends Activity {
	private static String TAG = "VideoActivity";
	private VideoView mVideoView;
	private MediaController mController;
	public static final double SMALL_WIN_H_SCALE = 0.60;
    public static final double SMALL_WIN_W_SCALE = 0.65;

    private boolean mShowDanmaku;
    private DanmakuView mDanmakuView;
    private DanmakuContext mDanmakuContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, "onCreate");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.video_activity);
        initView();
        resizeActivity();
//        playWithVideoView();
        playWithAction();
//        playNetVideo();
        MyApplication.addDestoryActivity("VideoActivity", this);
    }

    private void initView() {
        mDanmakuView = (DanmakuView) findViewById(R.id.danmaku_view);
        mDanmakuView.enableDanmakuDrawingCache(true);
        mDanmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                mShowDanmaku = true;
                mDanmakuView.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        mDanmakuContext = DanmakuContext.create();
        mDanmakuView.prepare(parser, mDanmakuContext);
    }

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    /**
     * 向弹幕View中添加一条弹幕
     * @param content
     *          弹幕的具体内容
     * @param  withBorder
     *          弹幕是否有边框
     */
    private void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = mDanmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(mDanmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        mDanmakuView.addDanmaku(danmaku);
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(mShowDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = "" + time + time;
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    
    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
    	mVideoView.pause();
		this.finish();
	}
    
    @SuppressLint("NewApi")
    private void resizeActivity(){
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        setFinishOnTouchOutside(true);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.gravity = Gravity.CENTER;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        layoutParams.width = (int) (displayMetrics.widthPixels * SMALL_WIN_W_SCALE);
        layoutParams.height = (int) (displayMetrics.heightPixels * SMALL_WIN_H_SCALE);
        LogUtil.d(TAG, "width: " + layoutParams.width);
    	LogUtil.d(TAG, "height: " + layoutParams.height);

        layoutParams.dimAmount = 0.0f;
        layoutParams.alpha = 1.0f;
        getWindow().setAttributes(layoutParams);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = getWindow().getDecorView();
        if(view != null) {
            view.setBackgroundResource(R.drawable.dialog_activity_bg);
        }
    }
    
    private boolean isVidoeFile(String filename) {
    	String str = filename.trim().toLowerCase();
    	if (str.endsWith(".mov") ||
    			str.endsWith(".wmv") ||
    			str.endsWith(".mpg") ||
    			str.endsWith(".avi") ||
    			str.endsWith(".rm") ||
    			str.endsWith(".rmvb") ||
    			str.endsWith(".mkv") ||
    			str.endsWith(".mp4")) {
    		return true;
    	}
    	return false;
    }
    
    private void playWithVideoView() {
    	LogUtil.d(TAG, "playWithVideoView");
    	// ��ȡ������VideoView���
        mVideoView = (VideoView) findViewById(R.id.video);
//        mVideoView.setVisibility(View.VISIBLE);
        // ����MediaController����
        mController = new MediaController(this);
        //String path = Environment.getExternalStorageDirectory().getPath()+ "/DCIM/Camera/newland-01.mp4";//��·�������Զ���
        String videoFileName = Environment.getExternalStorageDirectory().getPath()+ "/DCIM/Camera";
        File file = new File(Environment.getExternalStorageDirectory().getPath()+ "/DCIM/Camera");
        File[] subFile = file.listFiles();
        String videoName = "";
        LogUtil.d(TAG, "subFile.length: " + subFile.length);
        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            // �ж��Ƿ�Ϊ�ļ���
            if (!subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getName();
                // �ж��Ƿ�ΪMP4��β
                if (isVidoeFile(filename)) {
                	videoName = filename;
                	break;
                }
            }
        }
        File video = new File(videoFileName + "/" + videoName);
        if (video.exists()) {
        	mVideoView.setVideoPath(video.getAbsolutePath()); // ��
            // ����videoView��mController��������
        	mVideoView.setMediaController(mController); // ��
            // ����mController��videoView��������
            mController.setMediaPlayer(mVideoView); // ��
            // ��VideoView��ȡ����
            mVideoView.requestFocus();
            mVideoView.start();
        }
    }
    
    private void playWithAction() {
    	LogUtil.d(TAG, "playWithAction");
    	String path = Environment.getExternalStorageDirectory().getPath()+ "/DCIM/Camera/Video/newland-01.mp4";
    	File file = new File(path);
    	Uri uri = Uri.fromFile(file);
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setDataAndType(uri, "video/*");
    	startActivity(intent);
    }
    
    private void playNetVideo() {
    	LogUtil.d(TAG, "playNetVideo");
    	String url = "http://v.pptv.com/show/30g0sxuB8SibSENw.html?vfm=bdvppzq&frp=v.baidu.com%2Fcomic_intro%2Fbrowse&kwid=18938";//ʾ����ʵ�������������Ƶ����
    	String extension = MimeTypeMap.getFileExtensionFromUrl(url);
    	String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    	Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
    	mediaIntent.setDataAndType(Uri.parse(url), mimeType);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.destoryActivity("VideoActivity");
        mShowDanmaku = false;
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }
}
