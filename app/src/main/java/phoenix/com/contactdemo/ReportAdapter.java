package phoenix.com.contactdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by guoyali
 * <p>
 * on 2018/5/2.
 * <p>
 * use diffutil默认adapter
 */
public class ReportAdapter extends ListAdapter<CallLogInfo,ReportAdapter.SimpleAdapterViewHolder> {
    private Context context;

    protected ReportAdapter(@NonNull DiffUtil.ItemCallback<CallLogInfo> diffCallback) {
        super(diffCallback);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public static final  DiffUtil.ItemCallback<PhoneInfo> CallBack = new DiffUtil.ItemCallback<PhoneInfo>() {
        @Override
        public boolean areItemsTheSame(PhoneInfo oldItem, PhoneInfo newItem) {
            return oldItem.phoneName.equals(newItem.phoneName);
        }

        @Override
        public boolean areContentsTheSame(PhoneInfo oldItem, PhoneInfo newItem) {
            return oldItem.phoneName.equals(newItem.phoneName);
        }
    };


    @NonNull
    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_report, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleAdapterViewHolder holder, int position) {
        CallLogInfo item = getItem(position);
        if(item!=null){
            holder.img.setImageResource(R.mipmap.ic_launcher_round);
            holder.name.setText( item.name);
            holder.number.setText(item.number);
            holder.date.setText(item.date);
            holder.time.setText(item.time);
        }
    }




    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView name;
        public TextView number;
        public TextView date;
        public TextView time;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                img =   itemView
                        .findViewById(R.id.imageView1);
                name = itemView
                        .findViewById(R.id.name);
                number = itemView
                        .findViewById(R.id.number);
                date = itemView
                        .findViewById(R.id.date);
                time = itemView
                        .findViewById(R.id.time);

            }

        }
    }

    public static final class  SimpleItemCallBack2 extends DiffUtil.ItemCallback<CallLogInfo>{


        @Override
        public boolean areItemsTheSame(CallLogInfo oldItem, CallLogInfo newItem) {
            return oldItem.date.equals(newItem.date);
        }

        @Override
        public boolean areContentsTheSame(CallLogInfo oldItem, CallLogInfo newItem) {
            return oldItem.date.equals(newItem.date);
        }
    }
}
