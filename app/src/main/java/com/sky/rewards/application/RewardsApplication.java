package com.sky.rewards.application;

import android.app.Application;

import com.sky.rewards.ioc.di.ApplicationComponent;
import com.sky.rewards.ioc.di.ApplicationModule;
import com.sky.rewards.ioc.di.DaggerApplicationComponent;

public class RewardsApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = createApplicationComponent();
    }

    public ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(
                new ApplicationModule()).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
