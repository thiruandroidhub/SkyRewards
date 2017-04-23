package com.sky.rewards.model;

import java.util.List;

public class RewardsRequest {

    private final String accountId;
    private final List<SubscriptionChannel> subscriptions;

    public RewardsRequest(String accountId, List<SubscriptionChannel> subscriptions) {
        this.accountId = accountId;
        this.subscriptions = subscriptions;
    }

    public String getAccountId() {
        return accountId;
    }

    public List<SubscriptionChannel> getSubscriptions() {
        return subscriptions;
    }
}
