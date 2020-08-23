package com.abanoub.unit.zyonlauncher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.abanoub.unit.zyonlauncher.R;
import com.abanoub.unit.zyonlauncher.model.PagerModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class LauncherPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<PagerModel> mAppsList;
    private LayoutInflater mInflater;

    public LauncherPagerAdapter(Context context, List<PagerModel> list){
        this.mContext = context;
        this.mAppsList = list;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mAppsList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup group = (ViewGroup) mInflater.inflate(R.layout.item_view_pager, container, false);

        final GridView gridView = group.findViewById(R.id.item_view_pager_apps);
        gridView.setAdapter(new LauncherAppAdapter(mContext, mAppsList.get(position).getApps()));

        container.addView(group);
        return group;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
