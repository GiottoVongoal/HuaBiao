package com.huabiao.aoiin.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.huabiao.aoiin.R;
import com.huabiao.aoiin.bean.ClassificationItemBean;
import com.huabiao.aoiin.bean.ClassificationListBean;
import com.huabiao.aoiin.model.SearchModel;
import com.huabiao.aoiin.selecttool.AddressSelector;
import com.huabiao.aoiin.selecttool.DataProvider;
import com.huabiao.aoiin.selecttool.SelectedListener;
import com.huabiao.aoiin.ui.interfaces.InterfaceManager;
import com.ywy.mylibs.base.BaseFragment;
import com.ywy.mylibs.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author 杨丽亚.
 * @PackageName com.huabiao.aoiin.ui.fragment
 * @date 2017-07-10 13:19
 * @description
 */

public class CheckTypeListFragment extends BaseFragment {

    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    int deep = 3;
    String typeId = "0";
    AddressSelector selector;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        getJsonObj("classificationlist1.json");
    }

    private void getJsonObj(String string) {
        SearchModel.getClassificationResult(getContext(), string, new InterfaceManager.CallBackCommon() {
            @Override
            public void getCallBackCommon(Object mData) {
                if (mData != null) {
                    ClassificationListBean bean = (ClassificationListBean) mData;
                    List<ClassificationItemBean> list = bean.getClassificationlist();
                    show(typeId, list);
                }
            }
        });
    }

    private void show(String id, final List<ClassificationItemBean> list) {
        if (typeId.equals("0")) {
            selector = new AddressSelector(getContext(), deep);
            selector.setDataProvider(id, list, new DataProvider() {
                @Override
                public void provideData(int currentDeep, String preId, DataReceiver receiver) {
                    //根据tab的深度和前一项选择的id，获取下一级菜单项
                    receiver.send();
                }

                @Override
                public void getNext(String id) {
                    typeId = id;
                    getJsonObj("classificationlist" + typeId + ".json");
                }
            });
            frameLayout.addView(selector.getView());
        } else {
            selector.getNextData(typeId, list);
        }
        selector.setSelectedListener(new SelectedListener() {
            @Override
            public void onAddressSelected(ArrayList<ClassificationItemBean> selectAbles) {
                String result = "";
                for (ClassificationItemBean selectAble : selectAbles) {
                    result += selectAble.getClassificationname() + " ";
                }
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.check_type_list_layout;
    }
}
