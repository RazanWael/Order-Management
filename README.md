# Order-Management
In this project, an order management system was implemented, having the following entities: 
1- a customer, with a unique id, first name, last name and a born date. 
#
2- a product, with an auto-incremeanting id, slug, name, reference, price, vat and whether it is stockable or not. 
#
3- a stock, with a unique id, the product id that it has, the quantity availabe, and an updatedAt date that is set once a stock is created.
#
4- an order, with a unique id, a customer id that has made this order and an orderedAt date reflecting the actual date when the object was created. 
#
5- a product_order, having many-to-one relationships with both the order and the product, along with the quantity, the price, and vat of the ordered product. 
#
Seperate entity, repository, controller, dto, and service were created for each of the mentioned above. 
#
The basic rest APIs were implemented for each of the enitites. All the APIs were secured, and as a result to be autherized in requesting them, bearer token was provided along with each request. This bearer token was created after doing a sign-up for a user, then doing a sign-in and finally using the token sent in the response body of the sign-in request. In addition, swagger was implemented. 
Following is a table summarizign the HTTP method, the used URL path, the HTTP status code and the description for each implemented API: 
Note: the basic requests (GetALL, GetByID, Post, Put, delete) were implemented as well as more advanced ones as shown. 

![Capture](https://user-images.githubusercontent.com/74601216/172477179-27317695-b72e-45e0-85a6-03dc9ac5278f.PNG)

#
Following are the same requests provided with a sample request and a sample response: 
![result1](https://user-images.githubusercontent.com/74601216/172477301-5cf845ce-5f37-4423-a41f-28ef9fa7b1e1.PNG)
![result2](https://user-images.githubusercontent.com/74601216/172477310-dc3b5d7b-8767-413c-bd17-71f22d5bec17.PNG)
![result3](https://user-images.githubusercontent.com/74601216/172477317-dc08a550-d73d-4d8e-970d-4ab9912783f1.PNG)
![result4](https://user-images.githubusercontent.com/74601216/172477333-1ed65568-e146-4f46-ac28-1f81abedd6bc.PNG)
![result5](https://user-images.githubusercontent.com/74601216/172477342-71fa5450-9cb6-4cfe-9aa7-cc4731afe23e.PNG)
