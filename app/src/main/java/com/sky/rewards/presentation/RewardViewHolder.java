package com.sky.rewards.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sky.rewards.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RewardViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.reward_name)
    TextView rewardName;

    public RewardViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void render(String rewardsName) {
        rewardName.setText(rewardsName);
    }
}
