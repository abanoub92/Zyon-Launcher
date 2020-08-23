package com.abanoub.unit.zyonlauncher.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abanoub.unit.zyonlauncher.R;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;

public class LauncherAppAdapter extends BaseAdapter {

    private Context mContext;
    private List<ResolveInfo> mAppsList;
    private LayoutInflater mInflater;

    public LauncherAppAdapter(Context context, List<ResolveInfo> list){
        this.mContext = context;
        this.mAppsList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mAppsList.size();
    }

    @Override
    public Object getItem(int i) {
        return mAppsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view;

        if (convertView == null){
            view = mInflater.inflate(R.layout.item_app, viewGroup, false);
        }else {
            view = convertView;
        }

        ConstraintLayout appLayout = view.findViewById(R.id.item_app_layout);
        ImageView appImage = view.findViewById(R.id.item_app_image);
        TextView appTitle = view.findViewById(R.id.item_app_title);

        appImage.setImageDrawable(mAppsList.get(position).activityInfo.loadIcon(mContext.getPackageManager()));
        appTitle.setText(mAppsList.get(position).activityInfo.loadLabel(mContext.getPackageManager()).toString());

        appLayout.setOnClickListener(v -> {
            Intent launchApp = mContext.getPackageManager()
                    .getLaunchIntentForPackage(mAppsList.get(position).activityInfo.packageName);
            if (launchApp != null)
                mContext.startActivity(launchApp);
        });

        return view;
    }
}
