# CoolWithCoule Design Document



## Introduction


#### Our team

Sylvia Mohos, Anthony Oweyeong, Jackson Ross, Ben Wilson


#### Who we are

We are an online store called “CoolWithCoule” that sells hilarious kitchenware.  Our products make great gifts, conversation starters, and something functional yet playful to brighten up your kitchen.




#### Our goals



* Visitors to our store will be able to browse through our products and add items to their cart.
* Visitors to our store will be able to search for a product by its name.
* If a visitor decides to purchase a product, they will be prompted to either sign in to an existing account or proceed as a guest.
* Once the customer is successfully signed in, they will be able to place their purchase.
* When an order is completed, the customer will be directed to a confirmation page.
* When an order is completed, the items will be removed from our inventory and the product’s available quantity will be updated.


#### Our dreams



* Customers with a registered account will receive promotions from our store which can be used toward their purchases.
* To create a separate space where our store manager/supervisor can log in to their Administration account in order to access and manage the store’s inventory manually.
* To implement a service that will enable an auto-reorder feature on specified inventory products that reach a minimum allowable quantity.
* When a product reaches a minimum allowable quantity, a text message notification will be sent to the Administrator(s).
* To create the ability to sort and review inventory reports to determine peak sales, best selling product and the opposite for forecasting business operations.
* To create the ability to track revenue and expenses
* To collect customer ratings on the products
* To assign permissions to employees in order to manage their level of authority when it comes to manipulating inventory
* To enable an authentication that when a new user decides to create an account, an email will be sent to that user to verify their account in order to log in to that account for the first time


## CoolWithCoule Design


## 1. Problem Statement

Our store needs to be able to support our many customers by offering a service where they can shop from the comfort of their home. As a result, our store needs to be able to organize and manage our inventory when those orders are placed.

**Guest: _a customer who visits the store and does not have a registered account_**

_**A guest needs to:**_



* View and browse our store’s products;
* Add items into the cart and proceed to the checkout process;
* Complete the checkout process by inputting their name and shipping address;
* Look up a product by its name;
* As guests purchase items, our inventory will decrease;

**Registered Customer: _a customer who has created an account to log in with_**

**A Registered Customer needs to:**



* View and browse our store’s product;
* Add items into the cart and proceed to the checkout process;
* Complete the checkout process by logging in to their account;
* Look up a product by its name;
* Review their order history;
* As customers purchase items, our inventory will decrease;

**Administrator: **the** _store Manager/Supervisor_**


**An Administrator needs to:**



* Log in to the Administrator’s page with a valid user id and password to authenticate the functionality to manually add, remove, and update inventory items as required;
* Receives a text message alert when a product quantity reaches a minimum allowable quantity;
* Look up a product by name or product id.


## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that_

_you are still debating internally that you might like help working through._



* What if a customer wants to return a product to the store?
* What will our strategy be for handling exceptions and error codes?
* What will happen if a customer purchases a product’s remaining inventory before another customer is able to complete their purchase while that product is in their cart?
* When will the inventory be decremented? What if there are multiple people checking out at the same time?


## 3. Use Cases

_This is where we work backwards from the customer and define what our customers_

_would like to do (and why). You may also include use cases for yourselves, or_

_for the organization providing the product to customers._


```
U1. As a Guest or a Registered Customer, I want to <view the store's products> when I <visit the webpage>. [GET]

U2. As a Guest or a Registered Customer, I want to <view the items in my cart> when I <click on the Cart icon>. [GET]

U3. As a Guest or a Registered Customer, I want to <add an item to my cart> when I <click "Add to Cart">. [POST / PUT]

U4. As a Guest or a Registered Customer, I want to <remove an item from my cart> when I <click "Remove from Cart">. [DELETE / PUT]

U5. As a Guest, I want to <checkout> when I <input my name and shipping address>. [POST]

U6. As a Registered Customer, I want to <checkout> when I <log in to my account>. [POST]

U7. As a Guest or Registered Customer,  I want to <search for a product by name> when I <input the product's name into the search field>. [GET]

U8. As a Registered Customer, I want to <view my order history> when I <am logged in to my account>. [GET]

U9. As a Registered Customer, I want to <update my profile> when <log in to my account> [PUT]

U10. As a Guest or a Registered Customer, I want to <update a product's inventory count> when I <purchase or return a product>. [PUT]

U11. As an Administrator, I want to <create an Administrator account for another Administrator> to gain access to the Inventory Management System when <I navigate to the website> [POST/PUT]

U12. As an Administrator, I want to <log in to my Inventory Management System account with my user ID and password> when <I navigate to the website> [POST/PUT]

U13. As an Administrator, I want to <search for a product, order or Customer> when I <input the product name, order id, or Customer id into the search field of my Admin Account>. [POST]

U14. As an Administrator, I want to <receive a notification> when <a product's inventory reaches a minimum allowable quantity> [GET]

U15. As an Administrator, I want to <view a current list of my inventory> when <I successfully log in to my account>. [GET]

U16. As an Administrator, I want to <auto-reorder product> when <it gets to a minimum allowable quantity> [PUT]

U17. As an Administrator, I want to <add and remove product> when <I purchase more inventory or when I sell inventory> [PUT]

U18. As an Administrator, I want to <retrieve a report that will show me the sales of a particular product> when I <input the string of that specific product>. [GET]
```



## 4. Proposed Architecture Overview

We decided that the Request, Activity, Result design pattern was well structured and flexible enough to allow for scalability.  These class names provide a clear representation of what the intended purpose of the object is. Because our program is designed to handle Post, Get, Put and Delete requests, we chose this particular architecture to organize those requests in a manner that is easy to understand, follow and execute.

![](images/design_document/GeneralClassDiagrams.png)

## 5. API


### Public Models

CustomerModel - a CustomerModel is what will be returned to the client when a new Customer is created. Intended to hide personal information about the customer to the front end (password, etc.)


### _5.1_ GETInventoryOfProductsRequest

GETInventoryOfProductsRequest is the first endpoint. There is no required data here since we are getting all the inventory displayed on the screen. The data that’s returned are the product’s name, price and the description. Any failure would not display the objects but this will not shut down the server.

![](images/design_document/GETInventoryOfProductsRequest.png)



### _5.2_ _GETProductRequest_

GETProductRequest is the second endpoint. The required data is the product’s name. The returned data is the product’s name. If an unknown product is being retrieved a ProductDoesNotExistException would be thrown, which will return a 400 response.



![](images/design_document/GETProductRequest.png)



### _5.3_ _POSTCheckoutRequest_

The third endpoint would be POSTCheckoutRequest. The required data would be the cart; the list of the products being bought. The customerModel is also a required data because it holds the information of the customer. If the cart is empty while trying to checkout a 400 response would be returned and if the product in the cart is either out of stock or a greater amount than the quantity stored is trying to be bought an OutOfStockException would be thrown which would return a 400 response. The returned data would be the POSTCheckoutRequestResult data which would end with a 200 response.




![](images/design_document/POSTCheckoutRequest.png)



### _5.4_ _PUTProductRequest Endpoint_

The fourth endpoint would be PUTProductRequest. The required data is the PUTProductRequest which has the product’s name. The return data would be PUTProductResult data which would then return a 200 response. If an error occurs such as a product not existing, a ProductNotFound exception would be thrown.


## 

![](images/design_document/PUTProductRequest.png)


### _5.5_ _PUTCustomerRequest Endpoint_

The next endpoint is the PUTCustomerRequest which requires the Account request, which is the Account information. If a customer doesn’t exist a CustomerNotFound exception would be thrown. If not, Customer’s data would be changed and a 200 would be returned.



![](images/design_document/PUTCustomerRequest.png)



###  _5.6_ _GETCustomerByEmailRequest_

The required data for the GETCustomerByEmailRequest endpoint includes the GETCustomerByEmailRequest which checks if a Customer exists based on a Customer’s email. If a customer does not exist, the return data will be a null Customer which will throw a CustomerDoesNotExistException resulting in a 400 error response.  If the Customer email is found, then the Customer’s password will be checked against the submitted password. If the submitted password doesn’t match the customer’s password, it will throw an InvalidPasswordException and return a null customer with a 400 error response. If the submitted password matches the customer’s password, the customer model will be returned via the GETCustomerEmailResult and pass a 200 status response.



![](images/design_document/GETCustomerByEmailRequest.png)



### _5.7_ _GETCustomerIdRequest_

The required data for the GETCustomerRequest endpoint includes the GETCustomerRequest which checks if a Customer exists based on a Customer’s id. If a customer does not exist, the return data will be a null Customer which will throw a CustomerDoesNotExistException resulting in a 400 error response. If the Customer Id is found, then the Customer’s data will be returned via the GETCustomerResult and pass a 200 status response.

![](images/design_document/GETCustomerById.png)


### _5.8_ _GETOrdersRequest_

The required data for the GETOrdersRequest endpoint includes the GETOrderRequest which checks if an Order exists based on an Order's id. If an Order does not exist, the return data will be a null Order which will throw an OrderDoesNotExistException resulting in a 400 error response.  If the Order Id is found, then the Order’s data will be returned via the GETOrderResult and pass a 200 status response.


![](images/design_document/GETOrdersRequest.png)



### _5.9 POSTCustomerRequest_

The required data for the POSTCustomerRequest endpoint includes the POSTCustomerRequest which checks if a Customer exists based on a Customer’s email. If a Customer with this email already exists, the return data will be a Customer object which will throw a CustomerAlreadyExistsException resulting in a 400 error response.  If the Customer email is not found, then a null Customer object will be returned via the POSTCustomerActivity which will then create and return a new Customer via the POSTCustomerResult and pass a 200 status response.



![](images/design_document/POSTCustomerRequest.png)



### _5.10_ _POSTProductRequest_

The required data for POSTProductRequest is the Add Product request, which will have the product’s information. This information will be saved to DynamoDB from the InventoryDao. The returned data would be the Product data to the AddProductResponse data which will be a 200 response.



![](images/design_document/POSTProductRequest.png)



### _5.11_ _DELETECustomerRequest_

The DELETECustomerRequest requires the Customer’s id. If the id doesn’t exist a CustomerNotFound exception would be thrown and a 400 response would be invoked. If everything is successful a DELETECustomerRequest will be returned and a 200 response would be passed.



![](images/design_document/DELETECustomerRequest.png)



### _5.12 DELETEProductRequest_

The DELETEProductRequest will require Product data such as its name. If an unknown product is requested a ProductNotFoundException would be thrown with a 400 status code. A Successful request will return a DELETEProductResponse data with a 200 status.



![](images/design_document/DELETEProductRequest.png)



## 6. Tables


<table>
  <tr>
   <td><strong>Table name</strong>
   </td>
   <td><strong>PartitionKey</strong>
   </td>
   <td><strong>SortKey</strong>
   </td>
   <td><strong>GSI</strong>
   </td>
  </tr>
  <tr>
   <td>Product
   </td>
   <td>Name(String)
   </td>
   <td>n/a
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Customer
   </td>
   <td>CustomerId(String)
   </td>
   <td>n/a
   </td>
   <td>email-index
   </td>
  </tr>
  <tr>
   <td>Order
   </td>
   <td>OrderId(String)
   </td>
   <td>n/a
   </td>
   <td>
   </td>
  </tr>
</table>



## 7. Pages



![](images/design_document/page1.png)

![](images/design_document/page2.png)

![](images/design_document/page3.png)

![](images/design_document/page4.png)

![](images/design_document/page5.png)

![](images/design_document/page6.png)

![](images/design_document/page7.png)
`
`