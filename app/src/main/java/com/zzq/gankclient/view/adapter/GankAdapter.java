package com.zzq.gankclient.view.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.zzq.gankclient.R;
import com.zzq.gankclient.data.FuliDataBean;
import com.zzq.gankclient.view.widget.RatioImageView;

import java.util.List;

public class GankAdapter extends PagedListAdapter<FuliDataBean.ResultsBean, GankAdapter.GankViewHolder> {

    private Context mContext;

    public GankAdapter(Context context, List<FuliDataBean.ResultsBean> data) {
        super(diffCallback);
        mContext = context;
    }

    static DiffUtil.ItemCallback<FuliDataBean.ResultsBean> diffCallback = new DiffUtil.ItemCallback<FuliDataBean.ResultsBean>() {
        @Override
        public boolean areItemsTheSame(FuliDataBean.ResultsBean oldItem, FuliDataBean.ResultsBean newItem) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldItem.get_id().equals(newItem.get_id());
        }

        @Override
        public boolean areContentsTheSame(FuliDataBean.ResultsBean oldItem, FuliDataBean.ResultsBean newItem) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldItem.equals(newItem);
        }
    };

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gank, parent, false));
    }

    @Override
    public void onBindViewHolder(final GankViewHolder holder, int position) {
        FuliDataBean.ResultsBean resultsBean = getItem(position);
        holder.card.setTag(resultsBean.getDesc());
        Glide.with(mContext)
                .load(getItem(position).getUrl())
                .asBitmap()
                .centerCrop()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImageView)
                .getSize(new SizeReadyCallback() {
                    @Override
                    public void onSizeReady(int width, int height) {
                        if (!holder.card.isShown()) {
                            holder.card.setVisibility(View.VISIBLE);
                        }
                    }
                });

        if (itemClickListener != null) {
            itemClickListener.onItemClick(position, getItem(position));
        }
    }

    public static class GankViewHolder extends RecyclerView.ViewHolder {
        RatioImageView mImageView;
        View card;

        GankViewHolder(View view) {
            super(view);
            card = view;
            mImageView = view.findViewById(R.id.iv_item_gank);
            mImageView.setOriginalSize(50, 50);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, FuliDataBean.ResultsBean bean);
    }

    private OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }
}
