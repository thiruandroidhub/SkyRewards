package com.sky.rewards.provider;

import com.sky.rewards.model.RewardsResponse;
import com.sky.rewards.model.RewardStatus;
import com.sky.rewards.model.RewardsRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RewardsAccountCheckerShould {

    private RewardsAccountChecker rewardsAccountChecker;

    @Before
    public void setUp() throws Exception {
        // given
        rewardsAccountChecker = new RewardsAccountChecker();
    }

    @Test
    public void return_true_for_same_account() {
        // given
        rewardsAccountChecker.setRewardsResponse(new RewardsResponse(RewardStatus.ELIGIBLE, Collections.EMPTY_LIST));
        rewardsAccountChecker.setRewardsRequest(new RewardsRequest("123", Collections.EMPTY_LIST));

        // then
        assertThat(rewardsAccountChecker.isRequestForSameAccount(new RewardsRequest("123", Collections.EMPTY_LIST))).isTrue();
    }

    @Test
    public void return_false_for_different_account() {
        // given
        rewardsAccountChecker.setRewardsResponse(new RewardsResponse(RewardStatus.ELIGIBLE, Collections.EMPTY_LIST));
        rewardsAccountChecker.setRewardsRequest(new RewardsRequest("111", Collections.EMPTY_LIST));

        // then
        assertThat(rewardsAccountChecker.isRequestForSameAccount(new RewardsRequest("123", Collections.EMPTY_LIST))).isFalse();
    }

}