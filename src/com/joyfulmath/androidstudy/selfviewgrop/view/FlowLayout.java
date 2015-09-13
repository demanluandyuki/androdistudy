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
     * �洢���е�View�����м�¼ 
     */  
    private List<List<View>> mAllViews = new ArrayList<List<View>>();  
    /** 
     * ��¼ÿһ�е����߶� 
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
        // �洢ÿһ�����е�childView  
        List<View> lineViews = new ArrayList<View>();  
        int cCount = getChildCount();  
        // �������еĺ���  
        for (int i = 0; i < cCount; i++)  
        {  
            View child = getChildAt(i);  
            MarginLayoutParams lp = (MarginLayoutParams) child  
                    .getLayoutParams();  
            int childWidth = child.getMeasuredWidth();  
            int childHeight = child.getMeasuredHeight();  
  
            // ����Ѿ���Ҫ����  
            if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width)  
            {  
                // ��¼��һ�����е�View�Լ����߶�  
                mLineHeight.add(lineHeight);  
                // ����ǰ�е�childView���棬Ȼ�����µ�ArrayList������һ�е�childView  
                mAllViews.add(lineViews);  
                lineWidth = 0;// �����п�  
                lineViews = new ArrayList<View>();  
            }  
            /** 
             * �������Ҫ���У����ۼ� 
             */  
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;  
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin  
                    + lp.bottomMargin);  
            lineViews.add(child);  
        }  
        // ��¼���һ��  
        mLineHeight.add(lineHeight);  
        mAllViews.add(lineViews);  
  
        int left = 0;  
        int top = 0;  
        // �õ�������  
        int lineNums = mAllViews.size();  
        for (int i = 0; i < lineNums; i++)  
        {  
            // ÿһ�е����е�views  
            lineViews = mAllViews.get(i);  
            // ��ǰ�е����߶�  
            lineHeight = mLineHeight.get(i);  
  
            TraceLog.d("��" + i + "�� ��" + lineViews.size() + " , " + lineViews);  
            TraceLog.d("��" + i + "�У� ��" + lineHeight);  
  
            // ������ǰ�����е�View  
            for (int j = 0; j < lineViews.size(); j++)  
            {  
                View child = lineViews.get(j);  
                if (child.getVisibility() == View.GONE)  
                {  
                    continue;  
                }  
                MarginLayoutParams lp = (MarginLayoutParams) child  
                        .getLayoutParams();  
  
                //����childView��left,top,right,bottom  
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
     * ���������ӿؼ��Ĳ���ģʽ�ʹ�С ���������ӿؼ������Լ��Ŀ�͸� 
     */  
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// ������ĸ�����Ϊ�����õĲ���ģʽ�ʹ�С  
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);  
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);  
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);  
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);  

        TraceLog.i(sizeWidth + "," + sizeHeight);
        
     // �����warp_content����£���¼��͸�  
        int width = 0;  
        int height = 0;  
        /** 
         * ��¼ÿһ�еĿ�ȣ�width����ȡ����� 
         */  
        int lineWidth = 0;  
        /** 
         * ÿһ�еĸ߶ȣ��ۼ���height 
         */  
        int lineHeight = 0;  
  
        int cCount = getChildCount(); 
        
        for(int i=0;i<cCount;i++)
        {
        	View child = getChildAt(i);  
        	measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // �õ�child��lp  
            MarginLayoutParams lp = (MarginLayoutParams) child  
                    .getLayoutParams();
            //�����child view ����Ĵ�С
            int childwidth = child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            
         // ��ǰ�ӿռ�ʵ��ռ�ݵĸ߶�  
            int childHeight = child.getMeasuredHeight() + lp.topMargin  
                    + lp.bottomMargin; 
            
            if(lineWidth+childwidth>sizeWidth)
            {
            	//��ǰchild view�������Ҫ���С�
            	width = Math.max(lineWidth, childwidth);
            	lineWidth = childwidth;//�¼�¼
            	height +=lineHeight;
            	lineHeight = childHeight;
            }else
            {
            	//������
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
