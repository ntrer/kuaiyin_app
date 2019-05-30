package com.shushang.shushangjiatui.fragment.FiveFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shushang.shushangjiatui.R;
import com.shushang.shushangjiatui.base.BaseFragment;

public class FiveFragment extends BaseFragment {
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_five, null);
        return view;
    }
}
