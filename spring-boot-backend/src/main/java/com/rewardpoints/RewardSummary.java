package com.rewardpoints;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class RewardSummary {
    private List<MonthlyReward> monthlyRewards;
    private int totalRewardPoints;

    public RewardSummary(List<MonthlyReward> monthlyRewards, int totalRewardPoints) {
        this.monthlyRewards = monthlyRewards;
        this.totalRewardPoints = totalRewardPoints;
        sortMonthlyRewards();
    }

    public List<MonthlyReward> getMonthlyRewards() {
        return monthlyRewards;
    }

    public int getTotalRewardPoints() {
        return totalRewardPoints;
    }

    private void sortMonthlyRewards() {
        // Sort the monthly rewards list in chronological order (by Month)
        Collections.sort(monthlyRewards, Comparator.comparing(MonthlyReward::getMonth));
    }
}
