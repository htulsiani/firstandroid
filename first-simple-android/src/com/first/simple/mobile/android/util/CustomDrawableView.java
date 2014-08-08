package com.first.simple.mobile.android.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.first.simple.mobile.android.activities.R;

public class CustomDrawableView extends View {
	
	private long mTotalDiscSpace=0l;
    private long mFreeDicsSpace=0l;
    private int barTotalSize = 500;
	Paint p = new Paint();
	Point pt = new Point();
    
	public CustomDrawableView(Context context) {
        super(context, null);  
    }
	
	public CustomDrawableView(Context context, AttributeSet attrs) {
        super(context, attrs);  
    }
    
	@Override
    protected void onDraw(Canvas canvas) {
    	super.onDraw(canvas);
    	
		p.setAntiAlias(true);
		
		WindowManager wm = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		display.getSize(pt);
		barTotalSize = pt.x*2/3;
	
		long rectWidthFreeDiskSpace = getFreeDicsSpace() * barTotalSize / getTotalDiscSpace();
        long rectWidthUsedDiskSpace = barTotalSize - rectWidthFreeDiskSpace;
        
        if (rectWidthUsedDiskSpace <= 10) {
        	rectWidthUsedDiskSpace = 10;
        	rectWidthFreeDiskSpace=barTotalSize-10;
        }
		p.setColor(Color.WHITE);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(1f);
        canvas.drawRect(0, 0, rectWidthUsedDiskSpace, 50, p);
		p.setColor(Color.parseColor(this.getContext().getString(R.color.darkblue))); //darkblue
		p.setStyle(Paint.Style.FILL);
		canvas.drawRect(0, 0, rectWidthUsedDiskSpace, 50, p);
		
		p.setColor(Color.WHITE);
		p.setStyle(Paint.Style.STROKE);
		p.setStrokeWidth(1f);
		canvas.drawRect(rectWidthUsedDiskSpace, 0, rectWidthUsedDiskSpace+rectWidthFreeDiskSpace, 50, p);	
		p.setColor(Color.parseColor(this.getContext().getString(R.color.lightblue4))); //lightblue4
		p.setStyle(Paint.Style.FILL);
		canvas.drawRect(rectWidthUsedDiskSpace, 0, rectWidthUsedDiskSpace+rectWidthFreeDiskSpace, 50, p);
		        		
		p.setColor(Color.WHITE);
		p.setTextSize(25f);
		p.setTextAlign(Paint.Align.LEFT);
		String usedtext = this.getContext().getString(R.string.used);
		canvas.drawText(usedtext + ": " + displaySpaceHumanReadable(getTotalDiscSpace()-getFreeDicsSpace()), 10,35, p); 	
		p.setTextAlign(Paint.Align.RIGHT);
		String freetext = this.getContext().getString(R.string.free);
		canvas.drawText(freetext + ": " + displaySpaceHumanReadable(getFreeDicsSpace()), barTotalSize-10,35, p); 
		     
    } 
    
    public long getTotalDiscSpace() {
		return mTotalDiscSpace;
	}

	public void setTotalDiscSpace(long totalDiscSpace) {
		mTotalDiscSpace = totalDiscSpace;
		invalidate();
	    requestLayout();
	}

	public long getFreeDicsSpace() {
		return mFreeDicsSpace;
	}

	public void setFreeDicsSpace(long freeDicsSpace) {
		mFreeDicsSpace = freeDicsSpace;
		invalidate();
	    requestLayout();
	}
	

	/**
	* 
	* @param bytes
	* @return
	*/
	public static String displaySpaceHumanReadable(long bytes) {
		int unit = 1024;
		if (bytes < unit)
			return bytes + " bytes";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = ("KMGTPE").charAt(exp - 1) + ""; 
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
 
}

