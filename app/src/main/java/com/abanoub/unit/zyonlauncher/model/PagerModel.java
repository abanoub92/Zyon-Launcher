package com.abanoub.unit.zyonlauncher.model;

import android.content.pm.ResolveInfo;

import java.util.List;

public class PagerModel {

    private List<ResolveInfo> apps;

    public PagerModel(List<ResolveInfo> list){
        this.apps = list;
    }

    public List<ResolveInfo> getApps() {
        return apps;
    }
}
