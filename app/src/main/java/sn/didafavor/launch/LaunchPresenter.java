package sn.didafavor.launch;

/**
 * Created by pc on 2018/4/20.
 */

public interface LaunchPresenter {

    public void showAds();

    public void skipAds();

    public void setView(LaunchView launchView);


    public void destory();

}
