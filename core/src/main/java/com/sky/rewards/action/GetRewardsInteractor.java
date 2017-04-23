package com.sky.rewards.action;

import com.sky.rewards.provider.RewardsProvider;
import com.sky.rewards.model.RewardStatus;
import com.sky.rewards.model.Rewards;
import com.sky.rewards.model.RewardsRequest;
import com.sky.rewards.model.SubscriptionChannel;

import java.util.List;

public class GetRewardsInteractor {

    private final RewardsProvider rewardsProvider;
    private String accountId;
    private List<SubscriptionChannel> subscriptions;

    public GetRewardsInteractor(RewardsProvider rewardsProvider) {
        this.rewardsProvider = rewardsProvider;
    }

    public GetRewardsInteractor accountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public GetRewardsInteractor subscriptions(List<SubscriptionChannel> subscriptions) {
        this.subscriptions = subscriptions;
        return this;
    }

    public void getCustomerRewards(GetRewardsCallback getRewardsCallback) {
        rewardsProvider.getCustomerRewards(new RewardsRequest(accountId, subscriptions), getRewardsCallback);
    }

    public interface GetRewardsCallback {
         void onGetRewardsSuccess(RewardStatus status, List<Rewards> rewards);

        void onGetRewardFailure();
    }
}
