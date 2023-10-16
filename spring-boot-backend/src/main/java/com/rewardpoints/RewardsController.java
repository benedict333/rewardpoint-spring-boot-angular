package com.rewardpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rewards")
@CrossOrigin(origins = "http://localhost:4200")
public class RewardsController {
    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<RewardSummary> calculateRewardPoints(@RequestBody Transaction transaction) {
        rewardsService.addTransaction(transaction);

        RewardSummary rewardSummary = rewardsService.calculateRewards();

        return ResponseEntity.ok(rewardSummary);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions() {
        return ResponseEntity.ok(rewardsService.getTransactions());
    }
    
    @GetMapping("/calculate")
    public ResponseEntity<RewardSummary> calculateRewards() {
        RewardSummary rewardSummary = rewardsService.calculateRewards();
        return ResponseEntity.ok(rewardSummary);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Service is up and running");
    }
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearData() {
        rewardsService.clearData(); // Call the clearData method to reset the data
        return ResponseEntity.ok("Data has been cleared and reset");
    }
    @DeleteMapping("/deleteByTransactionId")
    public ResponseEntity<String> deleteByCustomerId(@RequestParam long transactionId) {
        rewardsService.deleteTransactionByTransactionId(transactionId);
        return ResponseEntity.ok("Transactions for customer ID " + transactionId + " have been deleted");
    }
}
