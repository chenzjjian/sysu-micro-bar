package com.softwaredesign.microbar;

import android.app.Application;

import java.util.LinkedHashSet;

/**
 * Created by mac on 16/7/2.
 */
public class MyApplication extends Application {

    private LinkedHashSet<Integer> recentlyWatches;

    @Override
    public void onCreate() {
        recentlyWatches = new LinkedHashSet<>();
        super.onCreate();
    }

    public LinkedHashSet<Integer> getRecentlyWatches() {
        return recentlyWatches;
    }

    public void setRecentlyWatches(LinkedHashSet<Integer> recentlyWatches) {
        this.recentlyWatches = recentlyWatches;
    }
}
