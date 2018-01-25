package sn.didafavor.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pc on 2018/1/9.
 */

public class CategoryItemListeners implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private Context context;
    private ItemClickListeners itemClickListeners;

    public CategoryItemListeners(Context context, ItemClickListeners itemClickListeners) {
        this.context = context;
        this.itemClickListeners = itemClickListeners;
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
    }

    public interface  ItemClickListeners {
        void onClick(View view,int postion);
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        float x = e.getX();
        float y =  e.getY();
        View clidView = rv.findChildViewUnder(x,y);
        if (clidView != null && itemClickListeners != null && gestureDetector.onTouchEvent(e)){
            itemClickListeners.onClick(clidView,rv.getChildAdapterPosition(clidView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


}
