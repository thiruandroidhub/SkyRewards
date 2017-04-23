package com.sky.rewards.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sky.rewards.R;
import com.sky.rewards.application.RewardsApplication;
import com.sky.rewards.model.RewardStatus;
import com.sky.rewards.model.Rewards;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RewardsActivity extends AppCompatActivity implements RewardsView {

    @Inject
    RewardsPresenter rewardsPresenter;

    @BindView(R.id.rewards)
    RecyclerView rewrdsRecyclerView;

    @BindView(R.id.account_id)
    EditText enterAccountIdEditText;

    @BindView(R.id.user_ineligible)
    TextView userIneligibleText;

    @BindView(R.id.invalid_account_error)
    TextView invalidAccountErrorText;

    @BindView(R.id.server_error)
    TextView serverErrorText;

    private RewardsAdapter rewardsAdapter;
    private String accountId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final RewardsApplication rewardsApplication = (RewardsApplication) getApplication();
        rewardsApplication.getApplicationComponent().inject(this);

        setContentView(R.layout.activity_rewards);

        if (savedInstanceState != null) {
            accountId = savedInstanceState.getString("account_id");
        }

        initializeButterKnife();
        rewardsPresenter.attachView(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("account_id", enterAccountIdEditText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.get_rewards)
    public void onGetRewardsClicked(View view) {
        clearViews();
        accountId = enterAccountIdEditText.getText().toString();
        rewardsPresenter.onGetRewards(accountId);
    }

    private void initializeButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public void showRewards(List<Rewards> rewards) {
        rewrdsRecyclerView.setVisibility(View.VISIBLE);
        rewardsAdapter.clear();
        rewardsAdapter.addAll(rewards);
        rewardsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showIneligible() {
        userIneligibleText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInvalidAccount() {
        invalidAccountErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void initView() {
        intiAccountIdView();
        initializeAdapter();
        initializeRecyclerView();
        rewardsPresenter.onGetRewards(accountId);
    }

    private void intiAccountIdView() {
        if (accountId != null) {
            enterAccountIdEditText.setText(accountId);
        }
    }

    private void clearViews() {
        rewrdsRecyclerView.setVisibility(View.GONE);
        userIneligibleText.setVisibility(View.GONE);
        invalidAccountErrorText.setVisibility(View.GONE);
        serverErrorText.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        serverErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        rewardsPresenter.onDestroy();
        super.onDestroy();
    }

    private void initializeAdapter() {
        rewardsAdapter = new RewardsAdapter();
    }

    private void initializeRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewrdsRecyclerView.setLayoutManager(linearLayoutManager);
        rewrdsRecyclerView.setHasFixedSize(true);
        rewrdsRecyclerView.setAdapter(rewardsAdapter);
    }
}
