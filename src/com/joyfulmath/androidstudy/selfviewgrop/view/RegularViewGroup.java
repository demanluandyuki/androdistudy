package com.joyfulmath.androidstudy.selfviewgrop.view;

import com.joyfulmath.androidstudy.TraceLog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class RegularViewGroup extends ViewGroup {
	
	
	public RegularViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public RegularViewGroup(Context context)
	{
		super(context);
	}
	
	public RegularViewGroup(Context context, AttributeSet attrs,int defstyle) {
		super(context, attrs,defstyle);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int cCount = getChildCount();
		int cWidth = 0;
		int cHeight = 0;
		MarginLayoutParams cParams = null;
		TraceLog.i(String.valueOf(cCount));
		for(int i=0;i<cCount;i++)
		{
			View childView = getChildAt(i);
			cWidth = childView.getMeasuredWidth();
			cHeight = childView.getMeasuredHeight();
			cParams = (MarginLayoutParams) childView.getLayoutParams();
			int cl = 0, ct = 0, cr = 0, cb = 0;
			
			switch (i)  
            {  
            case 0:  
                cl = cParams.leftMargin;  
                ct = cParams.topMargin;  
                break;  
            case 1:  
                cl = getWidth() - cWidth - cParams.leftMargin  
                        - cParams.rightMargin;  
                ct = cParams.topMargin;  
  
                break;  
            case 2:  
                cl = cParams.leftMargin;  
                ct = getHeight() - cHeight - cParams.bottomMargin;  
                break;  
            case 3:  
                cl = getWidth() - cWidth - cParams.leftMargin  
                        - cParams.rightMargin;  
                ct = getHeight() - cHeight - cParams.bottomMargin;  
                break;  
  
            }  
            cr = cl + cWidth;  
            cb = cHeight + ct;  
            childView.layout(cl, ct, cr, cb);
            TraceLog.i("cl"+String.valueOf(cl)+"\t ct"+String.valueOf(ct)+"\t cr"+String.valueOf(cr)+"\t cb"+String.valueOf(cb));
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
//		TraceLog.i(String.valueOf(widthMode)+"\t"+String.valueOf(heightMode));
//		TraceLog.i(String.valueOf(sizeWidth)+"\t"+String.valueOf(sizeHeight));
		
		//Calculate children's width & height
		measureChildren(widthMeasureSpec, heightMeasureSpec);
		//if wrap_content, we shall using children's width & height
		int width = 0;
		int height = 0;
		
		int cCount = getChildCount();
		int cWidth = 0;
		int cHeight = 0;
		MarginLayoutParams cParams = null;
		
		// ���ڼ����������childView�ĸ߶�
		int lHeight = 0;
		// ���ڼ����ұ�����childView�ĸ߶ȣ����ո߶�ȡ����֮���ֵ
		int rHeight = 0;

		// ���ڼ����ϱ�����childView�Ŀ��
		int tWidth = 0;
		// ���ڼ�����������childiew�Ŀ�ȣ����տ��ȡ����֮���ֵ
		int bWidth = 0;
		
		View childView = null;
		TraceLog.i(String.valueOf(cCount));
		for(int i=0;i<cCount;i++)
		{
			childView = getChildAt(i);
			cWidth = childView.getMeasuredWidth();
			cHeight = childView.getMeasuredHeight();
			cParams = (MarginLayoutParams) childView.getLayoutParams();
			
			if(i== 0 || i== 1)
			{
				tWidth+=cWidth+cParams.leftMargin+cParams.rightMargin;
			}

			if (i == 2 || i == 3)
			{
				bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
			}

			if (i == 0 || i == 2)
			{
				lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
			}

			if (i == 1 || i == 3)
			{
				rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
			}		
			
		}
		
		width = Math.max(tWidth, bWidth);
		height = Math.max(lHeight, rHeight);
		
		/**
		 * �����wrap_content����Ϊ���Ǽ����ֵ
		 * ����ֱ������Ϊ�����������ֵ
		 */
		setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
				: width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
				: height);
	}


	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}
	
	
	
}
