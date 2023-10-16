package com.rewardpoints;

import java.time.Month;

public class MonthlyReward {
    private Month month;
    private int rewardPoints;

    public MonthlyReward(Month month, int rewardPoints) {
        this.month = month;
        this.rewardPoints = rewardPoints;
    }

    public Month getMonth() {
        return month;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }
}
