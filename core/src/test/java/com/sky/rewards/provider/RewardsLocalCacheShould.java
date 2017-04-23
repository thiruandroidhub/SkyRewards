package com.sky.rewards.provider;

import com.sky.rewards.action.GetRewardsInteractor;
import com.sky.rewards.model.RewardsResponse;
import com.sky.rewards.model.RewardStatus;
import com.sky.rewards.model.RewardsRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardsLocalCacheShould {

    private static final RewardsRequest REWARDS_REQUEST = new RewardsRequest("123", Collections.EMPTY_LIST);
    private RewardsLocalCache rewardsLocalCache;

    @Mock
    private RewardsProvider rewardsNetworkGateway;

    @Mock
    private GetRewardsInteractor.GetRewardsCallback getRewardsCallback;

    @Mock
    private RewardsAccountChecker rewardsAccountChecker;

    @Before
    public void setUp() throws Exception {
        // given
        rewardsLocalCache = new RewardsLocalCache(rewardsNetworkGateway, rewardsAccountChecker);
    }

    @Test
    public void call_network_gateway_for_no_cached_rewards() {
        // when
        when(rewardsAccountChecker.isRequestForSameAccount(REWARDS_REQUEST)).thenReturn(false);
        rewardsLocalCache.getCustomerRewards(REWARDS_REQUEST, getRewardsCallback);

        // then
        verify(rewardsNetworkGateway).getCustomerRewards(REWARDS_REQUEST, rewardsLocalCache);
    }

    @Test
    public void return_cached_rewards_for_the_same_account() {
        // given
        when(rewardsAccountChecker.isRequestForSameAccount(REWARDS_REQUEST)).thenReturn(true);
        when(rewardsAccountChecker.getRewardsResponse()).thenReturn(new RewardsResponse(RewardStatus.ELIGIBLE, Collections.EMPTY_LIST));

        // when
        rewardsLocalCache.getCustomerRewards(REWARDS_REQUEST, getRewardsCallback);

        // then
        verify(getRewardsCallback).onGetRewardsSuccess(RewardStatus.ELIGIBLE, Collections.EMPTY_LIST);
        verify(rewardsNetworkGateway, times(0)).getCustomerRewards(REWARDS_REQUEST, rewardsLocalCache);
    }

    @Test
    public void clear_cache_on_failure() {
        // given
        when(rewardsAccountChecker.isRequestForSameAccount(REWARDS_REQUEST)).thenReturn(false);
        rewardsLocalCache.getCustomerRewards(REWARDS_REQUEST, getRewardsCallback);

        // when
        rewardsLocalCache.onGetRewardFailure();

        verify(rewardsAccountChecker).clearCache();
    }

}