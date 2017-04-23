package com.sky.rewards.provider;

import com.sky.rewards.action.GetRewardsInteractor;
import com.sky.rewards.model.RewardsResponse;
import com.sky.rewards.model.RewardStatus;
import com.sky.rewards.model.Rewards;
import com.sky.rewards.model.RewardsRequest;

import java.util.List;

public class RewardsLocalCache implements RewardsProvider, GetRewardsInteractor.GetRewardsCallback {

    private final RewardsProvider rewardsProvider;
    private final RewardsAccountChecker rewardsAccountChecker;

    private GetRewardsInteractor.GetRewardsCallback getRewardsCallback;

    public RewardsLocalCache(RewardsProvider rewardsProvider, RewardsAccountChecker rewardsAccountChecker) {
        this.rewardsProvider = rewardsProvider;
        this.rewardsAccountChecker = rewardsAccountChecker;
    }

    @Override
    public void getCustomerRewards(RewardsRequest rewardsRequest, GetRewardsInteractor.GetRewardsCallback getRewardsCallback) {
        if (rewardsAccountChecker.isRequestForSameAccount(rewardsRequest)) {
            getRewardsCallback.onGetRewardsSuccess(
                    rewardsAccountChecker.getRewardsResponse().getRewardStatus(),
                    rewardsAccountChecker.getRewardsResponse().getRewards());
            return;
        }
        rewardsAccountChecker.setRewardsRequest(rewardsRequest);
        this.getRewardsCallback = getRewardsCallback;
        rewardsProvider.getCustomerRewards(rewardsRequest, this);
    }

    @Override
    public void onGetRewardsSuccess(RewardStatus status, List<Rewards> rewards) {
        rewardsAccountChecker.setRewardsResponse(new RewardsResponse(status, rewards));
        this.getRewardsCallback.onGetRewardsSuccess(
                rewardsAccountChecker.getRewardsResponse().getRewardStatus(),
                rewardsAccountChecker.getRewardsResponse().getRewards());
    }

    @Override
    public void onGetRewardFailure() {
        rewardsAccountChecker.clearCache();
        this.getRewardsCallback.onGetRewardFailure();
    }
}
