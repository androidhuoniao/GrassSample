package com.grass;

import java.util.ArrayList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import com.grass.core.bean.BaseSampleItemInfo;
import com.grass.core.event.EventOfChangeFragment;
import com.grass.fragment.MainSampleListFragment;
import com.grass.mediastore.ImageItemInfo;
import com.grass.mediastore.ImageStore;
import com.grass.recyclerview.fragment.SampleListFragment;
import com.orhanobut.logger.Logger;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "test";
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.contentContainer, new MainSampleListFragment()).commit();
        loadImages();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void loadImages() {
        Observable<ArrayList<ImageItemInfo>> observable = Observable.create(
                new Observable.OnSubscribe<ArrayList<ImageItemInfo>>() {

                    @Override
                    public void call(Subscriber<? super ArrayList<ImageItemInfo>> subscriber) {
                        Logger.i("rx", "call work in " + Thread.currentThread().getName());
                        ArrayList<ImageItemInfo> list = ImageStore.queryImages(MainActivity.this);
                        if (list != null && !list.isEmpty()) {
                            subscriber.onNext(list);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new IllegalAccessException("没有数据"));
                        }

                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<ArrayList<ImageItemInfo>>() {
            @Override
            public void onCompleted() {
                Logger.i("rx", "onCompleted " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Logger.i("rx", "onError");
            }

            @Override
            public void onNext(ArrayList<ImageItemInfo> imageItemInfos) {
                Logger.i("rx", "onNext: " + imageItemInfos.size() + " " + Thread.currentThread().getName());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_recyclerview) {
            jumpTopRecyclerViewFragment();
        } else if (id == R.id.nav_dagger2) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //============事件处理======================

    @Subscribe
    public void onEventOfChangeFragment(EventOfChangeFragment event) {
        changeFragment(event.getInfo());
    }

    private void jumpTopRecyclerViewFragment() {
        Fragment fragment = mFragmentManager.findFragmentByTag("RecyclerView");
        mFragmentManager.beginTransaction().add(R.id.contentContainer, new SampleListFragment(), "RecyclerView")
                .commit();
    }

    public void changeFragment(BaseSampleItemInfo<Fragment> info) {
        for (int i = 0; i < 10; i++) {
            test(i);
            Log.i(TAG, "changeFragment: " + i);
        }
        if (info == null) {
        }
        try {
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.contentContainer, info.getSample().newInstance());
            ft.addToBackStack(null);
            ft.commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private String test(int i) {
        return "grass" + i;
    }
}
