package sn.didafavor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import sn.didafavor.listing.MovieListingActivity;
import sn.didafavor.adapter.CustomViewPagerAdapter;
import sn.didafavor.adapter.MenuItemRecyclerAdapter;
import sn.didafavor.data.CategoryData;
import sn.didafavor.fragments.MainFragment;
import sn.didafavor.fragments.TabNetFragment;
import sn.didafavor.listeners.CategoryItemListeners;
import sn.didafavor.widget.CustomViewPager;


/** main activity
 * Created by pc on 2018/1/5.
 */

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ActionBar ab;

    //declare the tabs action var
    private ImageView bar_music, bar_net, bar_friends, bar_search;
    private CustomViewPager viewPager;
    private ArrayList<ImageView> tabs = new ArrayList<>();

    private RecyclerView recyclerView;
    private List<CategoryData> lists;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = findViewById(R.id.fd);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                 R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if (getActionBar() != null)
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getActionBar() != null)
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        initView();
        initData();
        setViewPager();
        initListeners();
    }

    private void setActionBar() {
        // set the actionbar
        Toolbar myToolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setHomeButtonEnabled(true);
        ab.setTitle("");
    }

    private void initView(){
        recyclerView = findViewById(R.id.rv_catalogue_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        bar_music = findViewById(R.id.iv_bar_music);
        bar_net = findViewById(R.id.iv_bar_net);
        bar_friends = findViewById(R.id.iv_bar_friends);
        bar_search = findViewById(R.id.iv_search);
        viewPager = findViewById(R.id.main_viewpager);

    }

    private void initData(){
        lists = new ArrayList<CategoryData>();
        lists.add(new CategoryData(0, R.drawable.movie, getString(R.string.movie)));
        lists.add(new CategoryData(0, R.drawable.music, getString(R.string.music)));
        lists.add(new CategoryData(0, R.drawable.news, getString(R.string.news)));
        MenuItemRecyclerAdapter adapter = new MenuItemRecyclerAdapter(getBaseContext(), lists);
        recyclerView.setAdapter(adapter);
    }

    public void  setViewPager() {
        tabs.add(bar_music);
        tabs.add(bar_net);
        tabs.add(bar_friends);
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        customViewPagerAdapter.addFragment(new MainFragment());
        customViewPagerAdapter.addFragment(new TabNetFragment());
        customViewPagerAdapter.addFragment(new TabNetFragment());
        viewPager.setAdapter(customViewPagerAdapter);
        viewPager.setCurrentItem(1);
        bar_music.setSelected(true);
    }
    private void initListeners(){
        recyclerView.addOnItemTouchListener(new CategoryItemListeners(getBaseContext(), new CategoryItemListeners.ItemClickListeners() {
            @Override
            public void onClick(View view, int postion) {
                enterCategory(view,postion);
            }
        }));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                     swithTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bar_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        bar_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        bar_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
    }

    private void enterCategory(View view, int postion) {
        switch (lists.get(postion-1).getTitle()){
            case "movie":
                Intent intent = new Intent(MainActivity.this, MovieListingActivity.class);
                if (intent.resolveActivity(getPackageManager())!= null){
                    startActivity(intent);
                }
                break;
            case "music":
                break;
            default:
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    private void  swithTabs(int postion){
        for (int i = 0; i< tabs.size();i++){
            if(i == postion){
                tabs.get(i).setSelected(true);
            } else {
                tabs.get(i).setSelected(false);
            }
        }
    }


}
