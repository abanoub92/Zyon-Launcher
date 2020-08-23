package com.abanoub.unit.zyonlauncher.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.abanoub.unit.zyonlauncher.R;
import com.abanoub.unit.zyonlauncher.adapter.LauncherAppAdapter;
import com.abanoub.unit.zyonlauncher.adapter.LauncherPagerAdapter;
import com.abanoub.unit.zyonlauncher.model.PagerModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mBottomBar;
    private GridView mBottomBarDrawer;
    private ViewPager mLauncherPager;

    private BottomSheetBehavior mBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize XML views
        mBottomBar = findViewById(R.id.launcher_bottom_bar);
        mBottomBarDrawer = findViewById(R.id.launcher_bottom_bar_drawer);
        mLauncherPager = findViewById(R.id.launcher_view_pager);

        mBehavior = BottomSheetBehavior.from(mBottomBar);
        mBehavior.setHideable(false);
        mBehavior.setPeekHeight(200, true);

        initLauncherDrawer(getLauncherApps());
        initLauncherPager();

    }

    /**
     * Initialize the drawer of launcher apps
     * @param list of apps
     */
    private void initLauncherDrawer(List<ResolveInfo> list){
        LauncherAppAdapter appAdapter = new LauncherAppAdapter(getApplicationContext(), list);
        mBottomBarDrawer.setAdapter(appAdapter);
    }

    /**
     * Get all the apps in launcher
     * @return a list of apps
     */
    private List<ResolveInfo> getLauncherApps(){
        List<ResolveInfo> apps = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> list = getApplicationContext()
                .getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo info : list){
            if (!apps.contains(info))
                apps.add(info);
        }

        return apps;
    }

    /**
     * Initialize view pager of launcher with apps adapter
     */
    private void initLauncherPager(){
        List<PagerModel> list = new ArrayList<>();

        list.add(new PagerModel(getLauncherApps()));
        list.add(new PagerModel(getLauncherApps()));
        list.add(new PagerModel(getLauncherApps()));

        LauncherPagerAdapter adapter = new LauncherPagerAdapter(getApplicationContext(), list);
        mLauncherPager.setAdapter(adapter);
    }

    /**
     * Fix scrolling error while the drawer in normal mode,
     * scroll only when drawer take the whole screen size
     * (we actually won't in this time, but for any one had face the scroll error)
     */
    private void fixDrawerScrollError(){
        mBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN && mBottomBarDrawer.getChildAt(0).getY() != 0)
                    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                if (newState == BottomSheetBehavior.STATE_DRAGGING && mBottomBarDrawer.getChildAt(0).getY() != 0)
                    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
        });
    }
}