package com.zzq.gankclient.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.zzq.gankclient.R;
import com.zzq.gankclient.data.FuliDataBean;

import java.util.List;

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankViewHolder> {

    private Context mContext;
    private List<FuliDataBean.ResultsBean> data;

    public GankAdapter(Context context, List<FuliDataBean.ResultsBean> data) {
        mContext = context;
        this.data = data;
    }

    public void setData(List<FuliDataBean.ResultsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gank,parent,false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(final GankViewHolder holder, int position) {
        FuliDataBean.ResultsBean resultsBean = data.get(position);
        holder.card.setTag(resultsBean.getDesc());
        Glide.with(mContext)
                .load(data.get(position).getUrl())
//                .centerCrop()
                .into(holder.mImageView)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        if (!holder.card.isShown()) {
                            holder.card.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    public static class GankViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        View card;
        GankViewHolder(View view) {
            super(view);
            card = view;
            mImageView = view.findViewById(R.id.iv_item_gank);
        }
    }
}
