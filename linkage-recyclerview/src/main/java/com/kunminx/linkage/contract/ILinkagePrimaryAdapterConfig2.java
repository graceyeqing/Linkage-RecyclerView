package com.kunminx.linkage.contract;
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
import android.view.View;

import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder;
import com.kunminx.linkage.adapter.viewholder.LinkagePrimaryViewHolder2;
import com.kunminx.linkage.bean.DefaultLeftBean;

/**
 * Create by KunMinX at 19/5/8
 */
public interface ILinkagePrimaryAdapterConfig2<T extends DefaultLeftBean> {

    /**
     * setContext
     *
     * @param context context
     */
    void setContext(Context context);

    /**
     * get layout res id
     *
     * @return layout res id
     */
    int getLayoutId();

    /**
     * get textView id of layout
     *
     * @return textView id of layout
     */
    int getGroupTitleViewId();

    int getGroupImageViewId();

    /**
     * get rootView id of layout
     *
     * @return rootView id of layout
     */
    int getRootViewId();

    /**
     * achieve the onBindViewHolder logic on outside
     * <p>
     * Note: Do not setOnClickListener in onBindViewHolder,
     * instead, you can deal with item click in method 'ILinkagePrimaryAdapterConfig.onItemSelected()'
     * or 'LinkageRecyclerView.OnPrimaryItemClickListener.onItemClick()'
     * <p>
     * and we suggest you get position by holder.getAdapterPosition
     *
     * @param holder   LinkagePrimaryViewHolder
     * @param item    title of this position
     * @param selected selected of this position
     */
    void onBindViewHolder(LinkagePrimaryViewHolder2 holder, boolean selected, T item);

    /**
     * on primary item clicked
     * and we suggest you get position by holder.getAdapterPosition
     *
     * @param holder LinkagePrimaryViewHolder
     * @param view   itemView
     * @param item  title of primary item
     */
    void onItemClick(LinkagePrimaryViewHolder2 holder, View view, T item);
}
