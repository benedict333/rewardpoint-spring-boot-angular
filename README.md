# rewardpoint-spring-boot-angular
Please check out this  [Live Demo](http://34.70.167.81/) 
<br>
<br>
This repo contains two applications -java springboot as the backend and angular for the front-end used for calculating reward points for a customer.
<br>
<br>
Question:
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
<br>
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.
<br>
<br>
<br>
Solution:
There are two applications here; a spring boot application to run in the back-end and an anguar for the front-end. There are some hardcoded transactions included in the code.
To test the code clone these applications and run them separately, spring boot on http://localhost:8080 and angular on http://localhost:4200. Enter this url (http://localhost:4200) on a browser and you will be able to view the hardcoded transactions took place in the previous three months and the reward points earned for each month. You will be able to add or delete the transactions and the reward points will be calculated  accordingly.

<br> 
Please note that the calculation of the reward points will be available for the past three months only. So if you add any transactions beyond these periods will not be considered for reward calculations.
<br>
![image](https://github.com/benedict333/rewardpoints-spring-boot-angular/assets/45373173/9f752659-b10f-4777-b910-bbe385d1b7d1)
