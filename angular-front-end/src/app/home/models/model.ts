export interface MonthlyReward {
    month: string;
    rewardPoints: number;
  }
  
  export interface RewardPointsData {
    monthlyRewards: MonthlyReward[];
    totalRewardPoints: number;
  }
  