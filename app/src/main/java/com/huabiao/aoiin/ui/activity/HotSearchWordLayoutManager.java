package com.huabiao.aoiin.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class HotSearchWordLayoutManager extends RecyclerView.LayoutManager {
    private int mChildHeight,mChildWidth;
    protected int mLastRecyclePosition=-1;
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler,RecyclerView.State state){
       super.onLayoutChildren(recycler,state);
        if (getItemCount()==0){
            detachAndScrapAttachedViews(recycler);
            return;
        }
        if (getChildCount()==0&&state.isPreLayout()){
            return;
        }
        if(mChildWidth==0||mChildHeight==0){
            View firstView=recycler.getViewForPosition(0);
            addView(firstView);
            measureChildWithMargins(firstView,0,0);
            mChildHeight=getDecoratedMeasuredHeight(firstView);
            mChildWidth=getDecoratedMeasuredWidth(firstView);
            removeAndRecycleView(firstView,recycler);
        }
        fill(recycler,state);//这里应该改为我们的model数据
    }
    @Override
    public boolean canScrollHorizontally(){
        return true;
    }
    @Override
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int realOffset;
        realOffset = fill(recycler, state, i);
        offsetChildrenHorizontal(-realOffset);
        return realOffset;
    }
    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        fill(recycler,state,0);
    }

    private int fill(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        recycleHideViews(recycler,state,i);
        int itemCont=getItemCount();
        if(i>=0){
            View child;
            int startPos=0;
            if(mLastRecyclePosition!=-1){
                startPos=mLastRecyclePosition+getChildCount()+1;
            }
            int left = getPaddingLeft();
            int top = getPaddingTop();
            if(getChildCount()>0){
                child=getChildAt(getChildCount()-1);
                int lastPosition=getPosition(child);
                startPos=lastPosition+1;
                left=getNextViewLeft(child);
            }
            for(int count=startPos;count<itemCont;count++){
                if(left-i>getWidth()-getPaddingRight()){
                    break;
                }
                child=recycler.getViewForPosition(count);
                addView(child);
                measureChildWithMargins(child,0,0);
                layoutDecoratedWithMargins(child, left, top, left + mChildWidth, top + mChildHeight);
                left += mChildWidth;
            }

        }
        return i;
    }

    private void recycleHideViews(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        int childCount=getChildCount();
        if(childCount>0&&i!=0){
            mLastRecyclePosition=-1;
            for (int count=childCount-1;count>=0;count--){
                View child=getChildAt(count);
                if(i>0){
                    if(getDecoratedRight(child)-i<getPaddingLeft()){
                        mLastRecyclePosition=Math.max(mLastRecyclePosition,getPosition(child));
                        removeAndRecycleView(child,recycler);
                    }else {
                        continue;
                    }
                }else{
                    if(getDecoratedLeft(child)-i<getWidth()-getPaddingRight()){
                        mLastRecyclePosition=getPosition(child);
                        removeAndRecycleView(child,recycler);
                    }else{
                        continue;
                    }
                }
            }
        }
    }
    public int getNextViewLeft(View view){
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                view.getLayoutParams();
        return getDecoratedRight(view)+ params.rightMargin;
    }
}
