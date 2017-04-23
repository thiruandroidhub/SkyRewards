package com.sky.rewards.ioc.di;

import com.sky.rewards.presentation.RewardsActivity;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, RetrofitTestServiceApiModule.class})
public interface ApplicationComponent {
    void inject(RewardsActivity rewardsActivity);
}
