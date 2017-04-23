package com.sky.rewards.provider;

import com.sky.rewards.model.RewardsRequest;
import com.sky.rewards.model.RewardsResponse;

public class RewardsAccountChecker {

    private RewardsResponse previousRewardsResponse;
    private RewardsRequest previousRewardsRequest;

    public boolean isRequestForSameAccount(RewardsRequest rewardsRequest) {
        return previousRewardsResponse != null
                && previousRewardsRequest != null
                && rewardsRequest.getAccountId().equals(previousRewardsRequest.getAccountId());
    }

    public void setRewardsRequest(RewardsRequest rewardsRequest) {
        previousRewardsRequest = rewardsRequest;
    }

    public RewardsResponse getRewardsResponse() {
        return previousRewardsResponse;
    }

    public void setRewardsResponse(RewardsResponse rewardsResponse) {
        previousRewardsResponse = rewardsResponse;
    }

    public void clearCache() {
        previousRewardsResponse = null;
        previousRewardsRequest = null;
    }
}
