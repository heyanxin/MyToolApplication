package newland.com.mytoolapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import newland.com.mytoolapplication.R;
import newland.com.mytoolapplication.utils.LogUtil;
import newland.com.mytoolapplication.view.DividerItemDecoration;

public class RecyclerviewActivity extends Activity implements View.OnClickListener{
    private static String TAG = "RecyclerviewActivity";
    private Context mContext;
    private List<String> mList = new ArrayList<String>();
    private RecyclerView mRecycleView;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    private Button mAddBtn, mRemoveBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        mContext = this;
        initView();
        initEvent();
    }

    private void initView() {
        mAddBtn = (Button) findViewById(R.id.add_btn);
        mRemoveBtn = (Button) findViewById(R.id.remove_btn);
        for (int i =0; i < 100; i++) {
            mList.add("测试" + i);
        }
        mRecycleView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(this, mList);
        mRecycleView.setAdapter(mRecyclerViewAdapter);
        LogUtil.d(TAG, "size: " + mRecyclerViewAdapter.getItemCount());
        mRecyclerViewAdapter.updata(mList);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initEvent() {
        mAddBtn.setOnClickListener(this);
        mRemoveBtn.setOnClickListener(this);

        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, position + " clicked", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mContext, position + " long clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btn:
                mRecyclerViewAdapter.addData(1);
                break;
            case R.id.remove_btn:
                mRecyclerViewAdapter.removeData(1);
                break;
        }
    }
}
