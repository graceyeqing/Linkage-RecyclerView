package com.kunminx.linkagelistview.ui;
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
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kunminx.linkage.LinkageRecyclerView;
import com.kunminx.linkage.LinkageRecyclerView2;
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder2;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryFooterViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryHeaderViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkageSecondaryViewHolder;
import com.kunminx.linkage.bean.BaseGroupedItem;
import com.kunminx.linkage.bean.DefaultLeftBean;
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig;
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig2;
import com.kunminx.linkage.contract.ILinkageSecondaryAdapterConfig;
import com.kunminx.linkagelistview.R;
import com.kunminx.linkagelistview.bean.ElemeGroupedItem;
import com.kunminx.linkagelistview.databinding.FragmentSwitchBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by KunMinX at 19/5/8
 */
public class SwitchSampleFragment extends Fragment {

    private static final int SPAN_COUNT_FOR_GRID_MODE = 2;
    private static final int MARQUEE_REPEAT_LOOP_MODE = -1;
    private static final int MARQUEE_REPEAT_NONE_MODE = 0;
    private FragmentSwitchBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_switch, container, false);
        mBinding = FragmentSwitchBinding.bind(view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLinkageData(mBinding.linkage);

        mBinding.linkage.setGridMode(true);

        mBinding.btnPreview.setOnClickListener(v -> {
            mBinding.linkage.setGridMode(!mBinding.linkage.isGridMode());
        });
    }

    private void initLinkageData(LinkageRecyclerView2 linkage) {
        Gson gson = new Gson();
        List<ElemeGroupedItem> items = gson.fromJson(getString(R.string.eleme_json),
                new TypeToken<List<ElemeGroupedItem>>() {
                }.getType());

        List<DefaultLeftBean> leftBeans = new ArrayList<>();
        String[] names = {"优惠","套餐","主食","饮料","套餐A","套餐B","套餐C"};
        for(int i =0;i<names.length;i++){
            DefaultLeftBean item = new DefaultLeftBean();
            item.setTitle(names[i]);
            leftBeans.add(item);
        }

        linkage.init(leftBeans,items, new ElemeLinkagePrimaryAdapterConfig(), new ElemeLinkageSecondaryAdapterConfig());
    }

    private class ElemeLinkagePrimaryAdapterConfig implements ILinkagePrimaryAdapterConfig2 {

        private Context mContext;

        public void setContext(Context context) {
            mContext = context;
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_linkage_primary;
        }

        @Override
        public int getGroupTitleViewId() {
            return R.id.tv_group2;
        }

        @Override
        public int getGroupImageViewId() {
            return R.id.iv_image2;
        }

        @Override
        public int getRootViewId() {
            return R.id.layout_group2;
        }

        @Override
        public void onBindViewHolder(LinkagePrimaryViewHolder2 holder, boolean selected, DefaultLeftBean item) {
            TextView tvTitle = ((TextView) holder.mGroupTitle);
            LinearLayout llback = (LinearLayout) holder.mLayout;
            tvTitle.setText(item.getTitle());

            llback.setBackgroundColor(mContext.getResources().getColor(
                    selected ? com.kunminx.linkage.R.color.colorPurple : com.kunminx.linkage.R.color.colorWhite));
//            tvTitle.setBackgroundColor(mContext.getResources().getColor(
//                    selected ? com.kunminx.linkage.R.color.colorPurple : com.kunminx.linkage.R.color.colorWhite));
            tvTitle.setTextColor(ContextCompat.getColor(mContext,
                    selected ? com.kunminx.linkage.R.color.colorWhite : com.kunminx.linkage.R.color.colorGray));
            tvTitle.setEllipsize(selected ? TextUtils.TruncateAt.MARQUEE : TextUtils.TruncateAt.END);
            tvTitle.setFocusable(selected);
            tvTitle.setFocusableInTouchMode(selected);
            tvTitle.setMarqueeRepeatLimit(selected ? MARQUEE_REPEAT_LOOP_MODE : MARQUEE_REPEAT_NONE_MODE);
        }

        @Override
        public void onItemClick(LinkagePrimaryViewHolder2 holder, View view, DefaultLeftBean item) {

        }
    }

    private class ElemeLinkageSecondaryAdapterConfig implements
            ILinkageSecondaryAdapterConfig<ElemeGroupedItem.ItemInfo> {

        private Context mContext;

        public void setContext(Context context) {
            mContext = context;
        }

        @Override
        public int getGridLayoutId() {
            return R.layout.adapter_eleme_secondary_grid;
        }

        @Override
        public int getLinearLayoutId() {
            return R.layout.adapter_eleme_secondary_linear;
        }

        @Override
        public int getHeaderLayoutId() {
            return com.kunminx.linkage.R.layout.default_adapter_linkage_secondary_header;
        }

        @Override
        public int getFooterLayoutId() {
            return 0;
        }

        @Override
        public int getHeaderTextViewId() {
            return R.id.secondary_header;
        }

        @Override
        public int getSpanCountOfGridMode() {
            return SPAN_COUNT_FOR_GRID_MODE;
        }

        @Override
        public void onBindViewHolder(LinkageSecondaryViewHolder holder,
                                     BaseGroupedItem<ElemeGroupedItem.ItemInfo> item) {

            ((TextView) holder.getView(R.id.iv_goods_name)).setText(item.info.getTitle());
            Glide.with(mContext).load(item.info.getImgUrl()).into((ImageView) holder.getView(R.id.iv_goods_img));
            holder.getView(R.id.iv_goods_item).setOnClickListener(v -> {
                //TODO
            });

            holder.getView(R.id.iv_goods_add).setOnClickListener(v -> {
                //TODO
            });
        }

        @Override
        public void onBindHeaderViewHolder(LinkageSecondaryHeaderViewHolder holder,
                                           BaseGroupedItem<ElemeGroupedItem.ItemInfo> item) {

            ((TextView) holder.getView(R.id.secondary_header)).setText(item.header);
        }

        @Override
        public void onBindFooterViewHolder(LinkageSecondaryFooterViewHolder holder,
                                           BaseGroupedItem<ElemeGroupedItem.ItemInfo> item) {

        }
    }
}
