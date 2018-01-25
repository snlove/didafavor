package sn.didafavor.utils;

import io.reactivex.disposables.Disposable;

/**
 * Created by pc on 2018/1/22.
 */

public class RxUtil {

    public static void unsubscribe(Disposable subscribe){
        if (subscribe != null && !subscribe.isDisposed()) {
                 subscribe.dispose();
        }
    }

    public static void unsubscribe(Disposable... subscribes){
        for(Disposable subscribe : subscribes){
            unsubscribe(subscribe);
        }
    }
}
