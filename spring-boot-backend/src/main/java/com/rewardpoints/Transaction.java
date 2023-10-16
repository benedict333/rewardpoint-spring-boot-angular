package com.rewardpoints;
import java.time.LocalDateTime;

public class Transaction {
    private static long transactionCounter = 0; // Counter for generating unique transaction IDs

    private long transactionId; // Unique transaction ID
    private long customerId;
    private double transactionAmount;
    private LocalDateTime transactionDate;

    // Constructor
    public Transaction(long customerId, double transactionAmount, LocalDateTime transactionDate) {
        this.transactionId = generateTransactionId();
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    private synchronized long generateTransactionId() {
        return transactionCounter++;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction [transactionId=" + transactionId + ", customerId=" + customerId + ", transactionAmount=" + transactionAmount
                + ", transactionDate=" + transactionDate + "]";
    }
}

