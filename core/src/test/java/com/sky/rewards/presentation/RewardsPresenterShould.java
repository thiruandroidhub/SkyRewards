package com.sky.rewards.presentation;

import com.sky.rewards.action.GetRewardsInteractor;
import com.sky.rewards.model.RewardStatus;
import com.sky.rewards.model.Rewards;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardsPresenterShould {

    @Mock
    private RewardsView rewardsView;

    @Mock
    private GetRewardsInteractor getRewardsInteractor;

    private RewardsPresenter rewardsPresenter;

    @Before
    public void setUp() throws Exception {
        // given
        when(getRewardsInteractor.accountId("123")).thenReturn(getRewardsInteractor);
        when(getRewardsInteractor.subscriptions(Collections.EMPTY_LIST)).thenReturn(getRewardsInteractor);
        rewardsPresenter = new RewardsPresenter(getRewardsInteractor);
    }

    @Test
    public void init_vew_on_attach() {
        // when
        rewardsPresenter.attachView(rewardsView);

        // then
        verify(rewardsView).initView();
    }

    @Test
    public void not_get_rewards_for_null_accountId() {
        // when
        rewardsPresenter.attachView(rewardsView);
        rewardsPresenter.onGetRewards(null);

        // then
        verify(getRewardsInteractor, times(0)).getCustomerRewards(rewardsPresenter);
    }

    @Test
    public void not_get_rewards_for_empty_accountId() {
        // when
        rewardsPresenter.attachView(rewardsView);
        rewardsPresenter.onGetRewards("");

        // then
        verify(getRewardsInteractor, times(0)).getCustomerRewards(rewardsPresenter);
    }

    @Test
    public void get_rewards_for_valid_accountId() {
        // when
        rewardsPresenter.attachView(rewardsView);
        rewardsPresenter.onGetRewards("123");

        // then
        verify(getRewardsInteractor).getCustomerRewards(rewardsPresenter);
    }

    @Test
    public void show_rewards_on_success() {
        // when
        rewardsPresenter.attachView(rewardsView);
        List<Rewards> rewards = Arrays.asList(Rewards.CHAMPION_LEAGUE_FINAL_TICKET);
        RewardStatus rewardStatus = RewardStatus.ELIGIBLE;
        rewardsPresenter.onGetRewardsSuccess(rewardStatus, rewards);

        // then
        verify(rewardsView).showRewards(rewards);
    }

    @Test
    public void show_invalid_account_on_success() {
        // when
        rewardsPresenter.attachView(rewardsView);
        List<Rewards> rewards = Arrays.asList(Rewards.CHAMPION_LEAGUE_FINAL_TICKET);
        RewardStatus rewardStatus = RewardStatus.INVALID_ACCOUNT;
        rewardsPresenter.onGetRewardsSuccess(rewardStatus, rewards);

        // then
        verify(rewardsView).showInvalidAccount();
    }

    @Test
    public void show_ineligible_on_success() {
        // when
        rewardsPresenter.attachView(rewardsView);
        List<Rewards> rewards = Arrays.asList(Rewards.CHAMPION_LEAGUE_FINAL_TICKET);
        RewardStatus rewardStatus = RewardStatus.INELIGIBLE;
        rewardsPresenter.onGetRewardsSuccess(rewardStatus, rewards);

        // then
        verify(rewardsView).showIneligible();
    }

    @Test
    public void show_error_on_failure() {
        // when
        rewardsPresenter.attachView(rewardsView);
        rewardsPresenter.onGetRewardFailure();

        // then
        verify(rewardsView).showError();
    }
}
