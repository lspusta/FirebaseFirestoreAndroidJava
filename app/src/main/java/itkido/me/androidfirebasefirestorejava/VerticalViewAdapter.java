package itkido.me.androidfirebasefirestorejava;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class VerticalViewAdapter extends RecyclerView.Adapter<VerticalViewAdapter.ViewHolder>{


    private static final String TAG = "CourseRecyclerView";
    private ArrayList<String> mTitle = new ArrayList<>();
    private Context mContext;

    public VerticalViewAdapter(Context context, ArrayList<String> title) {
        mTitle = title;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");


        holder.title.setText(mTitle.get(position));

    }

    @Override
    public int getItemCount() {
        return mTitle.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);



        }
    }
}
