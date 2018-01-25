package sn.didafavor.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/** movie item listeners
 * Created by pc on 2018/1/16.
 */

public class MovieItemListeners implements RecyclerView.OnItemTouchListener {

    public interface MovieItemClick{
        void onClick(View view, int postion);
    }
    private MovieItemClick movieItemClick;
    private GestureDetector gestureDetector;

    public MovieItemListeners(Context context,MovieItemClick movieItemClick){
        this.movieItemClick = movieItemClick;
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(),e.getY());
        if (childView != null && movieItemClick != null && gestureDetector.onTouchEvent(e)){
            movieItemClick.onClick(rv,rv.getChildAdapterPosition(childView));
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
