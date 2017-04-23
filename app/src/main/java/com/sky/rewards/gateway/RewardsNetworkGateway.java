package com.sky.rewards.gateway;

import com.sky.rewards.provider.RewardsProvider;
import com.sky.rewards.action.GetRewardsInteractor;
import com.sky.rewards.ioc.di.ApplicationScope;
import com.sky.rewards.model.RewardsResponse;
import com.sky.rewards.model.RewardsRequest;
import com.sky.rewards.model.SubscriptionChannel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@ApplicationScope
public class RewardsNetworkGateway implements RewardsProvider {

    private final RewardsServiceApi rewardsServiceApi;

    @Inject
    public RewardsNetworkGateway(RewardsServiceApi rewardsServiceApi) {
        this.rewardsServiceApi = rewardsServiceApi;
    }

    @Override
    public void getCustomerRewards(
            final RewardsRequest rewardsRequest,
            final GetRewardsInteractor.GetRewardsCallback getRewardsCallback) {

        rewardsServiceApi.getRewardsForAccount(
                rewardsRequest.getAccountId(),
                rewardsRequest.getSubscriptions())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RewardsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getRewardsCallback.onGetRewardFailure();
                    }

                    @Override
                    public void onNext(RewardsResponse rewardsResponse) {
                        getRewardsCallback.onGetRewardsSuccess(
                                rewardsResponse.getRewardStatus(),
                                rewardsResponse.getRewards());
                    }
                });
    }

    public interface RewardsServiceApi {
        @POST("/rewards")
        Observable<RewardsResponse> getRewardsForAccount(
                @Query("accountId") String accountId,
                @Body List<SubscriptionChannel> subscriptions);
    }
}
