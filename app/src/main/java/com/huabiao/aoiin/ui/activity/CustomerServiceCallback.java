package com.huabiao.aoiin.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aoiin-9 on 2017/8/9.
 */

public class CustomerServiceCallback <T>  extends ItemTouchHelper.Callback{

    private final RecyclerView.Adapter adapter;
    private List<T> dataList;
    private List<T> removeList;
//    private OnSwipeListener<T> mListener;

    public CustomerServiceCallback(@NonNull RecyclerView.Adapter adapter, @NonNull List<T> dataList) {
        this.adapter = checkIsNull(adapter);
        this.dataList = checkIsNull(dataList);
        removeList=new ArrayList<>();
    }

    private <T> T checkIsNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

//    public void setOnSwipedListener(OnSwipeListener<T> mListener) {
//        this.mListener = mListener;
//    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof CustomerServiceLayoutManager) {
            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // 移除 onTouchListener,否则触摸滑动会乱了
        viewHolder.itemView.setOnTouchListener(null);
        int layoutPosition = viewHolder.getLayoutPosition();
        T remove;
        if(direction==ItemTouchHelper.LEFT){
            removeList.add(0,dataList.get(layoutPosition));
            remove = dataList.remove(layoutPosition);
        }else{
            if(removeList.size()==0){
                return;
            }
            remove = removeList.get(0);
            if(!dataList.contains(remove)){
                dataList.add(0,remove);
                removeList.remove(0);
            }
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }
}
