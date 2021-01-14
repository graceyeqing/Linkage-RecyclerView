package com.kunminx.linkage.adapter;
/*
 * Copyright (c) 2018-present. KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder2;
import com.kunminx.linkage.bean.DefaultLeftBean;
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig;
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig2;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by KunMinX at 19/4/29
 */
public class LinkagePrimaryAdapter2<L extends DefaultLeftBean> extends RecyclerView.Adapter<LinkagePrimaryViewHolder2> {

    private List<L> mStrings;
    private Context mContext;
    private View mView;
    private int mSelectedPosition;

    private ILinkagePrimaryAdapterConfig2 mConfig;
    private OnLinkageListener mLinkageListener;

    public List<L> getStrings() {
        return mStrings;
    }

    public ILinkagePrimaryAdapterConfig2 getConfig() {
        return mConfig;
    }

    public int getSelectedPosition() {
        return mSelectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        mSelectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    public LinkagePrimaryAdapter2(List<L> strings, ILinkagePrimaryAdapterConfig2 config,
                                  OnLinkageListener linkageListener) {
        mStrings = strings;
        if (mStrings == null) {
            mStrings = new ArrayList<>();
        }
        mConfig = config;
        mLinkageListener = linkageListener;
    }

    public void initData(List<L> list) {
        mStrings.clear();
        if (list != null) {
            mStrings.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LinkagePrimaryViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mConfig.setContext(mContext);
        mView = LayoutInflater.from(mContext).inflate(mConfig.getLayoutId(), parent, false);
        return new LinkagePrimaryViewHolder2(mView, mConfig);
    }

    @Override
    public void onBindViewHolder(@NonNull final LinkagePrimaryViewHolder2 holder, int position) {

        // for textView MARQUEE available.
        holder.mLayout.setSelected(true);

        final int adapterPosition = holder.getBindingAdapterPosition();
        final L item = mStrings.get(adapterPosition);

        mConfig.onBindViewHolder(holder, adapterPosition == mSelectedPosition, item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLinkageListener != null) {
                    mLinkageListener.onLinkageClick(holder, item);
                }
                mConfig.onItemClick(holder, v, item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    /**
     * only for linkage logic of level primary adapter. not use for outside logic
     * users can archive onLinkageClick in configs instead.
     */
    public interface OnLinkageListener<L extends DefaultLeftBean> {
        void onLinkageClick(LinkagePrimaryViewHolder2 holder, L item);
    }
}
