package com.dyc.customviewgroupdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by apple on 2018/2/1.
 */

public class FirstCustomLayout extends ViewGroup {

    public FirstCustomLayout(Context context){
        super(context);
    }

    public FirstCustomLayout(Context context, AttributeSet attrs ){
        this(context, attrs ,0);
    }

    public FirstCustomLayout(Context context,AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //measure the width and height of each childView
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        int layoutWidth = 0;
        int layoutHeight = 0;
        int maxChildHeight = 0;
        for(int i = 0; i < count; i++){
            View child = getChildAt(i);
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();

            if(layoutWidth < getWidth()){
                //if the row is not full
                l = layoutWidth;
                r = l + childMeasureWidth;
                t = layoutHeight;
                b = t + childMeasureHeight;
            }else{
                //if the row is full
                layoutWidth = 0;
                layoutHeight = layoutHeight + maxChildHeight;
                maxChildHeight = 0;
                l = layoutWidth;
                r = l + childMeasureWidth;
                t = layoutHeight;
                b = t + childMeasureHeight;

            }
            layoutWidth = layoutWidth + childMeasureWidth;
            if(childMeasureHeight > maxChildHeight){
                maxChildHeight = childMeasureHeight;
            }
            //l:left position,relative to parent
            //assign a size and position to a view and all of its descendants
            child.layout(l,t,r,b);

        }

    }
}
