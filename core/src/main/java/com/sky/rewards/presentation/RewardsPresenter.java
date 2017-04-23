package com.sky.rewards.presentation;

import com.sky.rewards.action.GetRewardsInteractor;
import com.sky.rewards.model.RewardStatus;
import com.sky.rewards.model.Rewards;

import java.util.Collections;
import java.util.List;

public class RewardsPresenter implements GetRewardsInteractor.GetRewardsCallback {

    private final GetRewardsInteractor getRewardsInteractor;
    private final RewardsView EMPTY_REWARDS_VIEW = new EmptyRewardsView();
    private RewardsView rewardsView;

    public RewardsPresenter(GetRewardsInteractor getRewardsInteractor) {
        this.getRewardsInteractor = getRewardsInteractor;
        this.rewardsView = EMPTY_REWARDS_VIEW;
    }

    public void onGetRewards(String accountId) {
        if (isValidAccountId(accountId)) {
            getRewardsInteractor
                    .accountId(accountId)
                    .subscriptions(Collections.EMPTY_LIST)
                    .getCustomerRewards(this);
        }
    }

    private boolean isValidAccountId(String accountId) {
        return accountId != null && !accountId.equals("");
    }

    @Override
    public void onGetRewardsSuccess(RewardStatus rewardStatus, List<Rewards> rewards) {
        switch (rewardStatus) {
            case ELIGIBLE:
                rewardsView.showRewards(rewards);
                break;

            case INELIGIBLE:
                rewardsView.showIneligible();
                break;

            case INVALID_ACCOUNT:
                rewardsView.showInvalidAccount();
                break;
        }
    }

    @Override
    public void onGetRewardFailure() {
        rewardsView.showError();
    }

    public void attachView(RewardsView rewardsView) {
        this.rewardsView = rewardsView;
        this.rewardsView.initView();
    }

    public void onDestroy() {
        rewardsView = EMPTY_REWARDS_VIEW;
    }
}
