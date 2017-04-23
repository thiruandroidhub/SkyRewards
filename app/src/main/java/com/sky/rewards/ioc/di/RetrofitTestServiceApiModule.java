package com.sky.rewards.ioc.di;

import com.sky.rewards.gateway.RewardsMockInterceptor;
import com.sky.rewards.gateway.RewardsNetworkGateway;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class RetrofitTestServiceApiModule {

    private static final String WEB_SERVICE_BASE_URL = "https://net-sky.api.com/rewardsservice/";

    @Provides
    @ApplicationScope
    protected RewardsNetworkGateway.RewardsServiceApi provideRewardsServiceApi() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new RewardsMockInterceptor()).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(WEB_SERVICE_BASE_URL)
                .client(client)
                .build();

        return retrofit.create(RewardsNetworkGateway.RewardsServiceApi.class);
    }
}
