package com.sky.rewards.provider;

import com.sky.rewards.action.GetRewardsInteractor;
import com.sky.rewards.model.RewardsRequest;

public interface RewardsProvider {

    void getCustomerRewards(
            RewardsRequest rewardsRequest,
            GetRewardsInteractor.GetRewardsCallback getRewardsCallback);
}
