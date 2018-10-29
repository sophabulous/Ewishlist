## Team Learning Goals

 Our team has three major learning goals
  * Build a React application
  * Use external APIs
  * Implement a login system

## Focus for our application
 For this, our application will focus on
  
  #### Minimally viable functional UI (7 pages)
   * Tracking page (Consists of a search bar where the user can paste a URL and add it to the List page)
   * Includes a confirmation page where the user confirms that they will track the product
   * List page (a page where user can see a list of all the items that he/she is tracking)
   * Detail page (where the user can see details about a specific product that they are tracking, add notes, and view the most recent price change to the product)
   * Login page (where the user can log in to the app)
   * Sign up page (where the user can sign up for the app)
   * Profile page (where the user can see their basic information)
   
   #### A microservice dedicated to user management
   * Session management
   * Login token authentication

   #### A microservice dedicated to managing the products a user is tracking
   * Functionality for the users to receive email notifications when one of the items they are tracking has a price change. 
   * Price tracking - a recurring batch process for grabbing the current prices from sites
   * Functionality for the user to add and remove items from the wishlist.

   #### Database 
   * will store the information about the users information and the list of products the user is currently tracking, and their current price
   #### Use Cases
   * The use cases are in the use-cases.md

## Team Goals for this application

 Our Team goals are:
 * Create functional UI using React
 * Create Login System
 * Notifications
 * Use the data we get from external APIs (Best Buy and Walmart) to track prices of products.
 
## Features we are going to omit in the application
   * The feature that allows users to specify that they should be notified only if the product goes below a particular price range 
   * Users cannot update their profile information
   * Users cannot delete their account
  
