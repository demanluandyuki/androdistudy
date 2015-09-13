package com.joyfulmath.androidstudy.selfviewgrop.view;

import java.util.ArrayList;
import java.util.List;

import com.joyfulmath.androidstudy.TraceLog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class FlowLayout extends ViewGroup {

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public FlowLayout(Context context)
	{
		super(context);
	}
	
	public FlowLayout(Context context, AttributeSet attrs,int defstyle) {
		super(context, attrs,defstyle);
	}
	
	/** 
     * 存储所有的View，按行记录 
     */  
    private List<List<View>> mAllViews = new ArrayList<List<View>>();  
    /** 
     * 记录每一行的最大高度 
     */  
    private List<Integer> mLineHeight = new ArrayList<Integer>();
    
    /*first, calculator how many line.
     * and then, calculator each line 's view and regular size.
     * */
    
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mAllViews.clear();  
        mLineHeight.clear();  
  
        int width = getWidth();  
  
        int lineWidth = 0;  
        int lineHeight = 0;  
        // 存储每一行所有的childView  
        List<View> lineViews = new ArrayList<View>();  
        int cCount = getChildCount();  
        // 遍历所有的孩子  
        for (int i = 0; i < cCount; i++)  
        {  
            View child = getChildAt(i);  
            MarginLayoutParams lp = (MarginLayoutParams) child  
                    .getLayoutParams();  
            int childWidth = child.getMeasuredWidth();  
            int childHeight = child.getMeasuredHeight();  
  
            // 如果已经需要换行  
            if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width)  
            {  
                // 记录这一行所有的View以及最大高度  
                mLineHeight.add(lineHeight);  
                // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView  
                mAllViews.add(lineViews);  
                lineWidth = 0;// 重置行宽  
                lineViews = new ArrayList<View>();  
            }  
            /** 
             * 如果不需要换行，则累加 
             */  
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;  
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin  
                    + lp.bottomMargin);  
            lineViews.add(child);  
        }  
        // 记录最后一行  
        mLineHeight.add(lineHeight);  
        mAllViews.add(lineViews);  
  
        int left = 0;  
        int top = 0;  
        // 得到总行数  
        int lineNums = mAllViews.size();  
        for (int i = 0; i < lineNums; i++)  
        {  
            // 每一行的所有的views  
            lineViews = mAllViews.get(i);  
            // 当前行的最大高度  
            lineHeight = mLineHeight.get(i);  
  
            TraceLog.d("第" + i + "行 ：" + lineViews.size() + " , " + lineViews);  
            TraceLog.d("第" + i + "行， ：" + lineHeight);  
  
            // 遍历当前行所有的View  
            for (int j = 0; j < lineViews.size(); j++)  
            {  
                View child = lineViews.get(j);  
                if (child.getVisibility() == View.GONE)  
                {  
                    continue;  
                }  
                MarginLayoutParams lp = (MarginLayoutParams) child  
                        .getLayoutParams();  
  
                //计算childView的left,top,right,bottom  
                int lc = left + lp.leftMargin;  
                int tc = top + lp.topMargin;  
                int rc =lc + child.getMeasuredWidth();  
                int bc = tc + child.getMeasuredHeight();  
  
                TraceLog.i(child + " , l = " + lc + " , t = " + t + " , r ="  
                        + rc + " , b = " + bc);
                child.layout(lc, tc, rc, bc);  
                  
                left += child.getMeasuredWidth() + lp.rightMargin  
                        + lp.leftMargin;  
            }  
            left = 0;  
            top += lineHeight;  
        }  
	}
	
	
	/** 
     * 负责设置子控件的测量模式和大小 根据所有子控件设置自己的宽和高 
     */  
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 获得它的父容器为它设置的测量模式和大小  
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);  
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);  
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);  
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);  

        TraceLog.i(sizeWidth + "," + sizeHeight);
        
     // 如果是warp_content情况下，记录宽和高  
        int width = 0;  
        int height = 0;  
        /** 
         * 记录每一行的宽度，width不断取最大宽度 
         */  
        int lineWidth = 0;  
        /** 
         * 每一行的高度，累加至height 
         */  
        int lineHeight = 0;  
  
        int cCount = getChildCount(); 
        
        for(int i=0;i<cCount;i++)
        {
        	View child = getChildAt(i);  
        	measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到child的lp  
            MarginLayoutParams lp = (MarginLayoutParams) child  
                    .getLayoutParams();
            //这个是child view 本身的大小
            int childwidth = child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            
         // 当前子空间实际占据的高度  
            int childHeight = child.getMeasuredHeight() + lp.topMargin  
                    + lp.bottomMargin; 
            
            if(lineWidth+childwidth>sizeWidth)
            {
            	//当前child view加入后，需要换行。
            	width = Math.max(lineWidth, childwidth);
            	lineWidth = childwidth;//新记录
            	height +=lineHeight;
            	lineHeight = childHeight;
            }else
            {
            	//不换行
            	lineWidth +=childwidth;
            	lineHeight = Math.max(lineHeight, childHeight);
            }
            
            if(i == cCount-1)
            {
            	 width = Math.max(width, lineWidth);  
                 height += lineHeight;
            }
        }
        //EXACTLY using size pass from parent AT_MOST using wrap_content
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth  
                : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight  
                : height); 
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}
}
