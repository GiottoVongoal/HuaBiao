package com.ywy.mylibs.inter;



import com.ywy.mylibs.base.impl.IBaseView;

import java.util.List;

/**
 * Created by ywy on 2016/1/22.
 */
public interface IPageListView<T> extends IBaseView {

    void setAdapter(List<T> list);

    void loadMore(List<T> list);

    void onRefreshComplete();

    void onLoadMoreComplete();

    void showSuccess();

    void showFaild();

    void showEmpty();

}
