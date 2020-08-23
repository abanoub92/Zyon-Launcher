package com.abanoub.unit.zyonlauncher.model;

import android.graphics.drawable.Drawable;

public class AppModel {

    private String appName;
    private String packageName;
    private Drawable appIcon;


    public AppModel(){}

    public AppModel(String appName, String packageName, Drawable appIcon){
        this.appName = appName;
        this.packageName = packageName;
        this.appIcon = appIcon;
    }


    public String getAppName() {
        return appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }
}
