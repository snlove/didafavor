package sn.didafavor.widget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pc on 2018/1/11.
 */

public class CustomViewPager extends ViewPager {


    private PointF pointF = new PointF();
    OnSingleTouchListener onSingleUpListeners;

    public interface  OnSingleTouchListener {
        void  onSingleUp(View view);
    }

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = 0,  y = 0;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = ev.getX();
                y = ev.getY();
                if (getChildCount() > 1){
                    //contains than one vies the event handle by self,no allow to interrupt;
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (getChildCount() > 1){
                    //contains than one vies the event handle by self,no allow to interrupt;
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
             case MotionEvent.ACTION_UP:
                 if(PointF.length(ev.getX() - x,ev.getY() - y) < 5.0){
                     onSingleUp(this);
                     return  true;
                 }
                 break;


        }
        return super.onTouchEvent(ev);
    }

    private void onSingleUp(View view){
        if (onSingleUpListeners != null){
            onSingleUpListeners.onSingleUp(view);
        }
    }

    public void setOnSingleUpListeners(OnSingleTouchListener onSingleUpListeners) {
        this.onSingleUpListeners = onSingleUpListeners;
    }
}
