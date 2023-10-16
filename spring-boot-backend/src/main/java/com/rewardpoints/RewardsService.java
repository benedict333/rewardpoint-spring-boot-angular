package com.rewardpoints;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class RewardsService {
    private List<Transaction> transactions = new ArrayList<>();

    public RewardsService() {
        initializeTransactions();
    }

    private void initializeTransactions() {        

        transactions.add(new Transaction(1, 70.0, LocalDateTime.of(2023, Month.JULY, 5, 15, 0)));
        transactions.add(new Transaction(1, 60.0, LocalDateTime.of(2023, Month.JULY, 20, 15, 0)));      
        
        transactions.add(new Transaction(1, 95.0, LocalDateTime.of(2023, Month.AUGUST, 5, 15, 0)));       
        transactions.add(new Transaction(1, 88.0, LocalDateTime.of(2023, Month.AUGUST, 25, 15, 0)));
        
        transactions.add(new Transaction(1, 120.0, LocalDateTime.of(2023, Month.SEPTEMBER, 5, 15, 0)));
        transactions.add(new Transaction(1, 60.0, LocalDateTime.of(2023, Month.SEPTEMBER, 20, 15, 0)));          
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        // Create a custom comparator to sort transactions by date in ascending order
        Comparator<Transaction> dateComparator = Comparator.comparing(Transaction::getTransactionDate);

        // Sort transactions in chronological order
        List<Transaction> sortedTransactions = new ArrayList<>(transactions); // Create a copy
        sortedTransactions.sort(dateComparator);

        return sortedTransactions;
    }


    public void clearData(Transaction transaction) {
        transactions.clear(); // Clear all transactions
        transactions.add(transaction);
    }

    public RewardSummary calculateRewards() {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime threeMonthsAgo = currentDate.minusMonths(3).with(TemporalAdjusters.firstDayOfMonth());        
        int totalRewardPoints = 0;
        Map<Month, Integer> monthlyRewardPoints = new HashMap<Month, Integer>();

        // Iterate through transactions
        for (Transaction transaction : transactions) {
            LocalDateTime transactionDate = transaction.getTransactionDate();

            // Check if the transaction is within the last three months and not in the current month
            if (transactionDate.isAfter(threeMonthsAgo) && transactionDate.isBefore(currentDate.withDayOfMonth(1))) {
                int rewardPoints = calculateRewardPoints(transaction.getTransactionAmount());
                totalRewardPoints += rewardPoints;

                // Update monthly reward points for the transaction's month
                Month transactionMonth = transactionDate.getMonth();

                // If the month is not in the map, initialize it with 0 points
                monthlyRewardPoints.putIfAbsent(transactionMonth, 0);
                monthlyRewardPoints.put(transactionMonth, monthlyRewardPoints.get(transactionMonth) + rewardPoints);
            }
        }

        // Generate monthly rewards list
        List<MonthlyReward> monthlyRewards = new ArrayList<>();
        for (Month month : monthlyRewardPoints.keySet()) {
            int monthlyRewardPointsValue = monthlyRewardPoints.get(month);
            monthlyRewards.add(new MonthlyReward(month, monthlyRewardPointsValue));
        }

        return new RewardSummary(monthlyRewards, totalRewardPoints);
    }
    private int calculateRewardPoints(double transactionAmount) {
        int rewardPoints = 0;

        if (transactionAmount > 100) {
            // Calculate reward points for the amount spent over $100.
            double amountOver100 = transactionAmount - 100;
            rewardPoints += (int) (amountOver100 * 2);
            transactionAmount = 100; // Set transaction amount to $100 to calculate the next tier
        }

        if (transactionAmount > 50) {
            // Calculate reward points for the amount spent between $50 and $100.
            rewardPoints += (int) (transactionAmount - 50);
        }

        return rewardPoints;
    }



    public void clearData() {
        transactions.clear(); // Clear all transactions
        initializeTransactions(); // Reinitialize with hardcoded data
    }
    public void deleteTransactionByTransactionId(long transactionId) {
        transactions.removeIf(transaction -> transaction.getTransactionId() == transactionId);        
    }
}