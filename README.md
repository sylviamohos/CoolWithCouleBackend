# CoolWithCoule Online Store

## Introduction

# Our team 
Sylvia Mohos, Anthony Oweyeong, Jackson Ross, Ben Wilson

# Who we are
We are an online store called “CoolWithCoule” that sells funky kitchen accessories.  Our products make great gifts, conversation starters, and something functional yet playful to brighten up your kitchen.  
 
# Our goals
* Visitors to our store will be able to browse through our products and add items to their cart.  
* Visitors to our store will be able to search for a product by its name.
* If a visitor decides to purchase a product, they will be prompted to either sign in to an existing account or create an account.  
* Once the customer is successfully signed in, they will be able to place their purchase.  
* When an order is completed, the customer will be directed to a confirmation page.
* When an order is completed, the items will be removed from our inventory and the product’s available quantity will be updated.


# Our dreams
* Customers with a registered account will receive promotions from our store which can be used toward their purchases.
* To create a separate space where our store manager/supervisor can log in to their Administration account in order to access and manage the store’s inventory manually.  
* To implement a service that will enable an auto-reorder feature on specified inventory products that reach a minimum allowable quantity.  
* When a product reaches a minimum allowable quantity, a text message notification will be sent to the Administrator(s).
* To create the ability to sort and review inventory reports to determine peak sales, best selling product and the opposite for forecasting business operations.
* To create the ability to track revenue and expenses
* To collect customer ratings on the products
* To assign permissions to employees in order to manage their level of authority when it comes to manipulating inventory
* To enable an authentication that when a new user decides to create an account, an email will be sent to that user to verify their account in order to log in to that account for the first time

# CoolWithCoule Design
1. Problem Statement:

Our store needs to be able to support our many customers by offering a service where they can shop from the comfort of their home. As a result, our store needs to be able to organize and manage our inventory when those orders are placed.

Guest: a customer who visits the store and does not have a registered account
	A guest needs to:
* View and browse our store’s products;
* Add items into the cart and proceed to the checkout process;
* Complete the checkout process by inputting their name and shipping address;
* Look up a product by its name;
* As guests purchase items, our inventory will decrease;

Registered Customer: a customer who has created an account to log in with
	A Registered Customer needs to:
* View and browse our store’s product;
* Add items into the cart and proceed to the checkout process;
* Complete the checkout process by logging in to their account;
* Look up a product by its name;
* Review their order history;
* As customers purchase items, our inventory will decrease;

Administrator: the store Manager/Supervisor
  An Administrator needs to:
* Log in to the Administrator’s page with a valid user id and password to authenticate the functionality to manually add, remove, and update inventory items as required;
* Receives a text message alert when a product quantity reaches a minimum allowable quantity;
* Look up a product by name or product id.


2. Top Questions to Resolve in Review

* What if a customer wants to return a product to the store?
* What will our strategy be for handling exceptions and error codes?
* What will happen if a customer purchases a product’s remaining inventory before another customer is able to complete their purchase while that product is in their cart?
* When will the inventory be decremented? What if there are multiple people checking out at the same time?



3. Use Cases

U1. As a Guest or a Registered Customer, I want to <view the store’s products> when I <visit the webpage>. [GET]

U2. As a Guest or a Registered Customer, I want to <view the items in my cart> when I <click on the Cart icon>. [GET]

U3. As a Guest or a Registered Customer, I want to <add an item to my cart> when I <click “Add to Cart”>. [POST / PUT]

U4. As a Guest or a Registered Customer, I want to <remove an item from my cart> when I <click “Remove from Cart”>. [DELETE / PUT]

U5. As a Guest, I want to <checkout> when I <input my name and shipping address>. [POST]

U6. As a Registered Customer, I want to <checkout> when I <log in to my account>. [POST]

U7. As a Guest or Registered Customer,  I want to <search for a product by name> when I <input the product’s name into the search field>. [GET]

U8. As a Registered Customer, I want to <view my order history> when I <am logged in to my account>. [GET]

U9. As a Registered Customer, I want to <update my profile> when <log in to my account> [PUT]

U10. As a Guest or a Registered Customer, I want to <update a product’s inventory count> when I <purchase or return a product>. [PUT]

U11. As an Administrator, I want to <create an Administrator account for another Administrator> to gain access to the Inventory Management System when <I navigate to the website> [POST/PUT]

U12. As an Administrator, I want to <log in to my Inventory Management System account with my user ID and password> when <I navigate to the website> [POST/PUT]

U13. As an Administrator, I want to <search for a product, order or Customer> when I <input the product name, order id, or Customer id into the search field of my Admin Account>. [POST]

U14. As an Administrator, I want to <receive a notification> when <a product’s inventory reaches a minimum allowable quantity> [GET]
	
U15. As an Administrator, I want to <view a current list of my inventory> when <I successfully log in to my account> [GET}

U15. As an Administrator, I want to <view a current list of my inventory> when <I successfully log in to my account>. [GET]

U16. As an Administrator, I want to <auto-reorder product> when <it gets to a minimum allowable quantity> [PUT]

U17. As an Administrator, I want to <add and remove product> when <I purchase more inventory or when I sell inventory> [PUT]

U18. As an Administrator, I want to <retrieve a report that will show me the sales of a particular product> when I <input the string of that specific product> [GET]


  
 4. Proposed Architecture Overview


We decided that the Request, Activity, Result design pattern was well structured and flexible enough to allow for scalability.  
  These class names provide a clear representation of what the intended purpose of the object is. 
  Because our program is designed to handle Post, Get, Put and Delete requests, we chose this particular architecture to 
  organize those requests in a manner that is easy to understand, follow and execute.  
