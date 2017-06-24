package com.metrohospital.tgs.cococititask.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.metrohospital.tgs.cococititask.R;
import com.metrohospital.tgs.cococititask.datamodal.FeedModel;

import java.util.List;
public class frag_adapter extends RecyclerView.Adapter<frag_adapter.MyViewHolder> {
    private Context mContext;
    private List<FeedModel.Showcases> albumList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id,title, discription,year;
        public LinearLayout more_detail_layout;
        public ImageView more_details, less_details;
        public ImageView thumbnail, overflow;
        public MyViewHolder(final View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.id);
            title = (TextView) view.findViewById(R.id.title2);
            discription = (TextView) view.findViewById(R.id.discription);
            year = (TextView) view.findViewById(R.id.year);

        }
    }
    public frag_adapter(Context mContext, FeedModel albumList) {
        this.mContext = mContext;
        this.albumList = albumList.getShowcases();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);*/
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_grid, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        FeedModel.Showcases album = albumList.get(position);
        holder.id.setText(album.getId());
        holder.title.setText(album.getTitle());
        holder.discription.setText(album.getDescription());
        holder.year.setText(album.getYear());
    }
    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
