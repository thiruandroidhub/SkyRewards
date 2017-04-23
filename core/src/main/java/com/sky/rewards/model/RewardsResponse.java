package com.sky.rewards.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RewardsResponse {

    @JsonProperty
    private List<Rewards> rewards;
    @JsonProperty
    private RewardStatus rewardStatus;

    /**
     * Empty constructor for json mapping
     */
    public RewardsResponse() {

    }

    public RewardsResponse(RewardStatus rewardStatus, List<Rewards> rewards) {
        this.rewards = rewards;
        this.rewardStatus = rewardStatus;
    }

    public List<Rewards> getRewards() {
        return rewards;
    }

    public RewardStatus getRewardStatus() {
        return rewardStatus;
    }
}
