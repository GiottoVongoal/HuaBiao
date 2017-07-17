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
            removeAndRecycleView(firstView, recycler);
        }
        fill(recycler,state);
    }
    @Override
    public boolean canScrollHorizontally(){
        return true;
    }
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int realOffset;
        realOffset = fill(recycler, state, dx);
        offsetChildrenHorizontal(-realOffset);
        return realOffset;
    }
    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        fill(recycler,state,0);

    }

    private int fill(RecyclerView.Recycler recycler, RecyclerView.State state, int dx) {
        recycleHideViews(recycler,state,dx);
        int itemCont=getItemCount();
        if(dx>=0){
            View child;
            int startPos=0;
            if(mLastRecyclePosition!=-1){
                startPos=mLastRecyclePosition+getChildCount()+1;
            }
            int left = getPaddingLeft();
            int top = getPaddingTop();
            if(getChildCount()>0){
                child=getChildAt(getChildCount()-1);
//                int lastPosition=getPosition(child);
//                startPos=lastPosition+1;
                startPos=getPosition(child)+1;
                left=getNextViewLeft(child);
            }
            for(int i=startPos;i<itemCont;i++){
                if(left-dx>getWidth()-getPaddingRight()){
                    break;
                }
                child=recycler.getViewForPosition(i);
                addView(child);
                measureChildWithMargins(child,0,0);
                layoutDecoratedWithMargins(child, left, top, left + mChildWidth, top + mChildHeight);
                left += mChildWidth;
            }
        }
        return dx;
    }

    private void recycleHideViews(RecyclerView.Recycler recycler, RecyclerView.State state, int dx) {
        int childCount=getChildCount();
        if(childCount>0&&dx!=0){
            mLastRecyclePosition=-1;
            for (int i=childCount-1;i>=0;i--){
                View child=getChildAt(i);
                if(dx>0){
                    if(getDecoratedRight(child)-dx<getPaddingLeft()){
                        mLastRecyclePosition=Math.max(mLastRecyclePosition,getPosition(child));

                    }else {
                        continue;
                    }
                }else{
                    if(getDecoratedLeft(child)-dx<getWidth()-getPaddingRight()){
                        mLastRecyclePosition=getPosition(child);
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
