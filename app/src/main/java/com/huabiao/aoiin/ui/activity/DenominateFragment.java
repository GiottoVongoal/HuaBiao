package com.huabiao.aoiin.ui.activity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.CreatNameBean;
import com.huabiao.aoiin.bean.CreatNameBean.CreatnameListBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.huabiao.aoiin.ui.view.DenominateRotatePanLayout;
import com.huabiao.aoiin.wedgit.DrawLineChartView;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;



public class DenominateFragment extends BaseFragment implements DenominateRotatePanLayout.AnimationEndListener, View.OnClickListener {
    @Bind(R.id.rp_layout)
    DenominateRotatePanLayout rp;

    @Bind(R.id.go)
    ImageView goBtn;

    @Bind(R.id.tv_name)
    TextView name;

    @Bind(R.id.tv_trademarkclassification)
    TextView trademarkclassification;

    @Bind(R.id.tv_means)
    TextView means;

    @Bind(R.id.creat_name_line_chart)
    DrawLineChartView creat_name_line_chart;

    @Bind(R.id.denominate_trade_name_btn)
    Button denominate_trade_name_btn;

//    @Bind(R.id.editTextDialogUserInput)
    EditText userInput;

    private List<CreatnameListBean> list;


    public void endAnimation(int position) {
        goBtn.setEnabled(true);
        setCreatNameData(position);
    }

    @Override
    public void bindView(Bundle savedInstanceState) {


        denominate_trade_name_btn.setOnClickListener(this);
        refreshView(false);
        arrowView(true);

    }


    private void refreshView(final boolean isFirst) {

        SearchModel.getCreatName(getContext(), new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    CreatNameBean bean = (CreatNameBean) mData;
                    list = bean.getRecommendnameList();
                    if (list.size() > 0) {
                        setData();
                    }
                    rp.startRotate(-1);
                    if (isFirst) {
                        goBtn.setEnabled(false);
                    }
                }
            }
        });
    }

    private void setData() {
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            nameList.add(list.get(i).getLinechart().getTrademarkname());
        }
        rp.setStr(nameList);
        rp.setAnimationEndListener(this);
        goBtn.setOnClickListener(this);

    }

    private void setCreatNameData(int position) {
        name.setText(list.get(position).getLinechart().getTrademarkname());
        means.setText(list.get(position).getMeans());
        String classificationString = list.get(position).getLinechart().getClassificationid() + " - " + list.get(position).getLinechart().getTrademarkclassification();
        trademarkclassification.setText(classificationString);
        creat_name_line_chart.setLineChartBean(list.get(position).getLinechart());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go:
                refreshView(true);
                break;
            case R.id.arrow:
                arrowView(true);
                break;
            case R.id.denominate_trade_name_btn:
                tradeName(true);
                break;

        }
    }


    private  void tradeName(boolean b){
        LayoutInflater li = LayoutInflater.from(mContext);
        View alertView = li.inflate(R.layout.tradename_alertdialog_layout, null);
        userInput= (EditText) alertView.findViewById(R.id.editTextDialogUserInput);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);
        alertDialogBuilder.setView(alertView);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                denominate_trade_name_btn.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();

    }

    private void arrowView(boolean b) {


    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_denominate;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

}