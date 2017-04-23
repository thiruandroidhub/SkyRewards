package com.sky.rewards.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.rewards.R;
import com.sky.rewards.model.Rewards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RewardsAdapter extends RecyclerView.Adapter<RewardViewHolder> {

    private List<Rewards> rewards;

    public RewardsAdapter() {
        this.rewards = new ArrayList<>();
    }

    public void addAll(Collection<Rewards> collection) {
        rewards.addAll(collection);
    }

    @Override
    public RewardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.rewards_item, parent, false);

        return new RewardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RewardViewHolder holder, int position) {
        String rewardName = rewards.get(position).name();
        holder.render(rewardName);
    }

    @Override
    public int getItemCount() {
        return rewards.size();
    }

    public void clear() {
        rewards.clear();
    }
}
