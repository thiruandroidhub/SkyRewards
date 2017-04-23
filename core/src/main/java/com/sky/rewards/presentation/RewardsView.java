package com.sky.rewards.presentation;

import com.sky.rewards.model.Rewards;

import java.util.List;

public interface RewardsView {
    void showRewards(List<Rewards> rewardsList);

    void initView();

    void showError();

    void showIneligible();

    void showInvalidAccount();
}
