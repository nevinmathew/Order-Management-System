# Order-Management-System

This is a Spring boot application for an order management system that as the following functionalities:

● Create customers.

● Customers can create orders. It is assumed that the customer has already made the payment while creating the order.

● Customers are categorised as regular, gold, platinum.

● By default, a customer is regular.
  ○ Customer is promoted to gold when he has placed 10 orders
  ○ Customer is promoted to platinum when he has placed 20 orders

● Gold tier customers get 10% discount, platinum tier customers get 20% discount.

● When a customer creates an order, if he is a gold customer, an automatic 10% discount is applied on the order. 20% for platinum customers.

● Since it is assumed that the customer has already made the full payment during creation of the order, this discount information has to be kept safe by the application. We need to keep track of how much discount is given to which customer and for which order, so that customers can claim money back later.

● Cron job to send emails to customers when they are approaching the gold/platinum barriers.
