package com.sky.rewards.action;

import com.sky.rewards.provider.RewardsProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetRewardsInteractorShould {

    @Mock
    private RewardsProvider rewardsProvider;

    private GetRewardsInteractor getRewardsInteractor;

    @Before
    public void setUp() throws Exception {
        // given
        getRewardsInteractor = new GetRewardsInteractor(rewardsProvider);
    }

    @Test
    public void call_rewards_provider_to_get_rewards() {
        // TODO
    }
}