package com.kunminx.linkage.adapter.viewholder;
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


import android.view.View;

import androidx.annotation.NonNull;

import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig;
import com.kunminx.linkage.contract.ILinkagePrimaryAdapterConfig2;

/**
 * Create by KunMinX at 19/5/15
 */
public class LinkagePrimaryViewHolder2 extends BaseViewHolder {

    public View mGroupTitle;
    public View mLayout;
    public View mGroupImage;
    private ILinkagePrimaryAdapterConfig2 mConfig;

    public LinkagePrimaryViewHolder2(@NonNull View itemView, ILinkagePrimaryAdapterConfig2 config) {
        super(itemView);
        mConfig = config;
        mGroupTitle = itemView.findViewById(mConfig.getGroupTitleViewId());
        mGroupImage = itemView.findViewById(mConfig.getGroupImageViewId());
        //need bind root layout by users, because rootLayout may not viewGroup, which can not getChild(0).
        mLayout = itemView.findViewById(mConfig.getRootViewId());
    }
}
