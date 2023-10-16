

### Situation
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.



A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).



Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

### Solution
There are some hardcoded transactions included in the code:
On postman make a GET call http://localhost:8080/rewards/transactions

It will return all the transaction details
 Another GET call http://localhost:8080/rewards/calculate
 will return the monthly rewards for the past three months and the total reward points
 
 A POST to the same end point with a JSON body as given below will add more transactions and generate the points response accordingly:
 {
  "customerId": 12345,
  "transactionAmount":150.75,
  "transactionDate": "2023-08-10T15:30:00"
}
Headers: Content-Type  application/json
 


