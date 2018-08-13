package newland.com.mytoolapplication.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import newland.com.mytoolapplication.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHodler> {
    Context mContext;
    List<String> mList;

    public RecyclerViewAdapter (Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHodler(View view) {
            super(view);
            textView = view.findViewById(R.id.recycleview_text);
        }
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        MyViewHodler hodler = new MyViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(final MyViewHodler holder, int position) {
        String str = mList.get(position);
        holder.textView.setText(str);
        if (null != mOnItemClickListener) {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(v, position);
                }
            });

            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(v, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updata(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void addData(int position) {
        mList.add(position, "添加一条数据");
        notifyItemInserted(position);
        //notifyDataSetChanged();
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
}
