package com.sky.rewards.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.rewards.model.RewardsResponse;
import com.sky.rewards.model.RewardStatus;
import com.sky.rewards.model.Rewards;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RewardsMockInterceptor implements Interceptor {

    private static final String ELIGIBLE_ACCOUNT = "123";
    private static final String INELIGIBLE_ACCOUNT = "111";
    private static final String INVALID_ACCOUNT = "222";

    private static final List<Rewards> REWARDS_123 = Arrays.asList(Rewards.values());
    private static final List<Rewards> EMPTY_REWARDS = Collections.EMPTY_LIST;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Response intercept(Chain chain) throws IOException {
        String responseString;
        final Request request = chain.request();
        String query = request.url().query();
        String accountId = query.substring(query.indexOf("=") + 1);
        RewardsResponse rewardsResponse;

        switch (accountId) {
            case ELIGIBLE_ACCOUNT:
                rewardsResponse = new RewardsResponse(RewardStatus.ELIGIBLE, REWARDS_123);
                break;
            case INELIGIBLE_ACCOUNT:
                rewardsResponse = new RewardsResponse(RewardStatus.INELIGIBLE, EMPTY_REWARDS);
                break;
            case INVALID_ACCOUNT:
                rewardsResponse = new RewardsResponse(RewardStatus.INVALID_ACCOUNT, EMPTY_REWARDS);
                break;
            default:
               throw new RuntimeException("500 - server generic error");
        }

        responseString = mapper.writeValueAsString(rewardsResponse);

        Response response = new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();

        return response;
    }
}
