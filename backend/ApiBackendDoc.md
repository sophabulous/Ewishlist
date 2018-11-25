# Setup instructions for backend

### Product Service
* setup the db locally or the cloud one, make sure it is running.
* export the following:
     * DB_URL : database url without the username and password
     * DB_USER: db username. Need to set this only if the username is not the usual "username".
     * DB_PWD : the database password Need to set this only if the password is not the usual "password".
     * WALMART_API_KEY: api key for walmart
     
* run the code. Make sure the code runs in the same shell as where the environment variables are exported. 

the service will run the port mentioned in its applications.properties file

### User Service
* setup the db locally or the cloud one, make sure it is running.
* export the following:
     * DB_URL : database url without the username and password
     * DB_USER: db username. Need to set this only if the username is not the usual "username".
     * DB_PWD : the database password Need to set this only if the password is not the usual "password".
     
* run the code. Make sure the code runs in the same shell as where the environment variables are exported. 

the service will run the port mentioned in its applications.properties file

### Exporting environment variables
* If using the bash shell to run the application
```
export NAME=value
```
where NAME is the name of the environment variable and value is its value.
* If using an IDE, please set the environment variables in the IDE configuration

### Sample payloads for the different endpoints
Notes:
* refer to the swagger api docs for what each url template look like (format wise) and for what the status codes mean
* for all calls set the following headers:
    "Accept": "application/json"
    "Content-Type": "application/json"

#### User Service 
* login:
     localhost:8080/users/token/login
     * sample request 
     ```
          {
               "username": "new_user",
               "password": "new_user"
          }
     ```
     * sample response 
     ```
          {
               "username": "new_user",
               "sessionToken": "6a50c36e-8be7-44e5-9990-eec401c12f98",
               "expiryTime": "2018-11-25T13:52:58.106-0500"
          }
     ```


* New user:
l    localhost:8080/users/manage
     * sample request 
     ```
          {
               "username": "test_user",
               "password": "test_user",
               "email": "test_new@example.com"
          }
     ```
     * sample response 
       no response body

#### Product Service
* Add a product to the tracking list:
     localhost:9090/tracking/all
     * sample request
     ```
          {
               "url": https://www.walmart.com/ip/Timex-Men-s-Classic-Digital-Watch-Gold-Tone-Stainless-Steel-Expansion-Band/10727980",
              "loginToken": {
                    "username": "new_user",
                    "sessionToken": "6a50c36e-8be7-44e5-9990-eec401c12f98",
                    "expiryTime": "2018-11-25T13:43:52.852-0500"
               }
          }
     ```
     * sample response 
     ```
          {
               "productId": "10727980",
               "productName": "Timex Men's Classic Digital Watch, Gold-Tone Stainless Steel Expansion Band",
               "currentPrice": 28.2,
               "trackedPrice": null,
               "trackedTime": null,
               "vendor": "Walmart",
               "url": "https://www.walmart.com/ip/Timex-Men-s-Classic-Digital-Watch-Gold-Tone-Stainless-Steel-Expansion-Band/10727980"
          }
     ```

* Get all the products the user is tracking:
     localhost:9090/tracking/all
     * sample request
     ```
          {
               "username": "new_user",
               "sessionToken": "6a50c36e-8be7-44e5-9990-eec401c12f98",
               "expiryTime": "2018-11-25T13:43:52.852-0500"
          }
     ```
     * sample response 
     ````
          [
               {
                    "productId": null,
                    "productName": null,
                    "currentPrice": null,
                    "trackedPrice": null,
                    "trackedTime": null,
                    "vendor": null,
                    "url": "https://www.walmart.com/ip/Timex-Men-s-Classic-Digital-Watch-Gold-Tone-Stainless-Steel-Expansion-Band/10727980"
               }
          ]
     ```
* get product info:
     localhost:9090/tracking/10727980/info
     * sample request 
     ```
          {
               "url": "https://www.walmart.com/ip/Timex-Men-s-Classic-Digital-Watch-Gold-Tone-Stainless-Steel-Expansion-Band/10727980",
               "loginToken":{
                    "username": "new_user",
                    "sessionToken": "6a50c36e-8be7-44e5-9990-eec401c12f98",
                    "expiryTime": "2018-11-25T13:43:52.852-0500"
               }
          }
     ```     
     * sample response
     ```
          {
               "productId": null,
               "productName": "Timex Men's Classic Digital Watch, Gold-Tone Stainless Steel Expansion Band",
               "currentPrice": 28.200000762939453,
               "trackedPrice": null,
               "trackedTime": null,
               "vendor": null,
               "url": "https://www.walmart.com/ip/Timex-Men-s-Classic-Digital-Watch-Gold-Tone-Stainless-Steel-Expansion-Band/10727980"
          }
     ```

* delete product from wishlist:
     localhost:9090/tracking/10727980
     * sample request 
     ```
          {
               "url": "https://www.walmart.com/ip/Timex-Men-s-Classic-Digital-Watch-Gold-Tone-Stainless-Steel-Expansion-Band/10727980",
               "loginToken": 
                    {
                    "username": "new_user",
                    "sessionToken": "6a50c36e-8be7-44e5-9990-eec401c12f98",
                    "expiryTime": "2018-11-25T13:43:52.852-0500"
                    }
          }   
     ```