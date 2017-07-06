package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huabiao.aoiin.R;

/**
 * @author 杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.materialdesign
 * @date 2017-07-05 13:20
 * @description
 */
public class BottonNavigationViewFragment extends Fragment {

    public static BottonNavigationViewFragment newInstance(String info) {
        Bundle args = new Bundle();
        BottonNavigationViewFragment fragment = new BottonNavigationViewFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.tv);
        tvInfo.setText(getArguments().getString("info"));
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "hello", Snackbar.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
