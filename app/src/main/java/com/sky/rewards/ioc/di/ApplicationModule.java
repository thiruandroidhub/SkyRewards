package com.sky.rewards.ioc.di;

import com.sky.rewards.provider.RewardsAccountChecker;
import com.sky.rewards.provider.RewardsLocalCache;
import com.sky.rewards.action.GetRewardsInteractor;
import com.sky.rewards.gateway.RewardsNetworkGateway;
import com.sky.rewards.presentation.RewardsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @ApplicationScope
    protected RewardsPresenter proideWeatherPresenter(GetRewardsInteractor getRewardsInteractor) {
        return new RewardsPresenter(getRewardsInteractor);
    }

    @Provides
    @ApplicationScope
    protected GetRewardsInteractor provideGetRewardsInteractor(RewardsLocalCache rewardsLocalCache) {
        return new GetRewardsInteractor(rewardsLocalCache);
    }

    @Provides
    @ApplicationScope
    protected RewardsLocalCache provideRewardsLocalCache(
            RewardsNetworkGateway rewardsNetworkGateway,
            RewardsAccountChecker rewardsAccountChecker) {
        return new RewardsLocalCache(rewardsNetworkGateway, rewardsAccountChecker);
    }

    @Provides
    protected RewardsAccountChecker providesRequestRewardsAccountChecker() {
        return new RewardsAccountChecker();
    }
}
