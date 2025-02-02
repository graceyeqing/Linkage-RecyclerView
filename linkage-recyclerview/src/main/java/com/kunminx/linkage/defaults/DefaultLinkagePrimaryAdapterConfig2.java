package com.kunminx.linkage.defaults;
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
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.kunminx.linkage.R;
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder2;
import com.kunminx.linkage.bean.DefaultLeftBean;
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig;
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig2;

/**
 * Create by KunMinX at 19/5/8
 */
public class DefaultLinkagePrimaryAdapterConfig2 implements ILinkagePrimaryAdapterConfig2<DefaultLeftBean> {

    private static final int MARQUEE_REPEAT_LOOP_MODE = -1;
    private static final int MARQUEE_REPEAT_NONE_MODE = 0;
    private Context mContext;
    private OnPrimaryItemBindListener mListener;
    private OnPrimaryItemClickListner mClickListner;

    public void setListener(OnPrimaryItemBindListener listener,
                            OnPrimaryItemClickListner clickListner) {
        mListener = listener;
        mClickListner = clickListner;
    }

    @Override
    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.default_adapter_linkage_primary;
    }

    @Override
    public int getGroupTitleViewId() {
        return R.id.tv_group;
    }

    @Override
    public int getGroupImageViewId() {
        return R.id.iv_image;
    }

    @Override
    public int getRootViewId() {
        return R.id.layout_group;
    }

    @Override
    public void onBindViewHolder(LinkagePrimaryViewHolder2 holder, boolean selected, DefaultLeftBean item) {
        TextView tvTitle = ((TextView) holder.mGroupTitle);
        tvTitle.setText(item.getTitle());

        tvTitle.setBackgroundColor(mContext.getResources().getColor(selected ? R.color.colorPurple : R.color.colorWhite));
        tvTitle.setTextColor(ContextCompat.getColor(mContext, selected ? R.color.colorWhite : R.color.colorGray));
        tvTitle.setEllipsize(selected ? TextUtils.TruncateAt.MARQUEE : TextUtils.TruncateAt.END);
        tvTitle.setFocusable(selected);
        tvTitle.setFocusableInTouchMode(selected);
        tvTitle.setMarqueeRepeatLimit(selected ? MARQUEE_REPEAT_LOOP_MODE : MARQUEE_REPEAT_NONE_MODE);

        if (mListener != null) {
            mListener.onBindViewHolder(holder, item);
        }
    }

    @Override
    public void onItemClick(LinkagePrimaryViewHolder2 holder, View view, DefaultLeftBean item) {
        if (mClickListner != null) {
            mClickListner.onItemClick(holder, view, item);
        }
    }


    public interface OnPrimaryItemClickListner {
        /**
         * we suggest you get position by holder.getAdapterPosition
         *
         * @param holder primaryHolder
         * @param view   view
         * @param item  groupTitle
         */
        void onItemClick(LinkagePrimaryViewHolder2 holder, View view, DefaultLeftBean item);
    }

    public interface OnPrimaryItemBindListener {
        /**
         * Note: Please do not override rootView click listener in here, because of linkage selection rely on it.
         * and we suggest you get position by holder.getAdapterPosition
         *
         * @param primaryHolder primaryHolder
         * @param item         groupTitle
         */
        void onBindViewHolder(LinkagePrimaryViewHolder2 primaryHolder, DefaultLeftBean item);
    }
}
