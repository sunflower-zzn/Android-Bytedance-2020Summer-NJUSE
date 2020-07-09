package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

/**
 * 适配器
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private static final String TAG = "Exercise3";

    //数据list
    private List<Message> mDataset;

    //监听点击事件
    private final ListItemClickListener mOnClickListener;

    private static int viewHolderCount;

    public RvAdapter(List<Message> mDataset,ListItemClickListener listener) {
        this.mDataset = mDataset;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.im_list_item,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        //进行数据绑定
        Message message = mDataset.get(position);
        if(message.isOfficial()){
            holder.official.setVisibility(View.VISIBLE);
        }
        else{
            holder.official.setVisibility(View.INVISIBLE);
        }
        switch (message.getIcon()){
            case "TYPE_ROBOT":
                holder.icon.setImageResource(R.drawable.session_robot);
                break;
            case "TYPE_GAME":
                holder.icon.setImageResource(R.drawable.icon_micro_game_comment);
                break;
            case "TYPE_SYSTEM":
                holder.icon.setImageResource(R.drawable.session_system_notice);
                break;
            case "TYPE_STRANGER":
                holder.icon.setImageResource(R.drawable.session_stranger);
                break;
            case "TYPE_USER":
                holder.icon.setImageResource(R.drawable.icon_girl);
                break;
        }
        holder.title.setText(message.getTitle());
        holder.description.setText(message.getDescription());
        holder.time.setText(message.getTime());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final FrameLayout header;
        private final ImageView official;
        private final CircleImageView icon;
        private final TextView title;
        private final TextView description;
        private final TextView time;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            header = (FrameLayout)itemView.findViewById(R.id.iv_avatar_header) ;
            icon = (CircleImageView) header.findViewById(R.id.iv_avatar);
            official = (ImageView) header.findViewById(R.id.robot_notice);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            time = (TextView) itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
