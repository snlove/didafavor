package sn.didafavor.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by pc on 2018/1/12.
 */

public class CustomViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> lists = new ArrayList<>();

    public CustomViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        lists.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
