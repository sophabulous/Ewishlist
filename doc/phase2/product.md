## What we build for Phase 2
  
  During this phase we created the follwing:
  * React pages of the application for the front-end and the routes between the pages
  * For the backend, we have set up the database and created functions to execute CRUD
    operation for sign-up and login-in for the users
  * We created the functions to update the database products by adding and removing 
    products to the user's wishlist and updating the price in the database
  * We implemented the tracking endpoints for the front-end to connect with the back-end
  * We implemented the functions to fetch price information from the Walmart api when 
    the user enters a new product url to track
  * We implemented the email notifications and now we are able to send emails to the user
  
  #### Changes made to the product than what we proposed originally
  * We added more pages to the front-end for better user interaction and user flow
  * We added user tokens and other login/user details
  
## High level design of the software

  #### Front-end
  
  On the front-end, we built a seven-page web application using the React Framework. 
  The seven pages are:
  
  * Log-in page, a page when users can log-in
  * Sign-up page, a page where users can sign up for a new account
  * Tracking page, a page where users can enter a URL to a product and track it
  * Confirmation page, a page that appears right after the user paste the product URL and 
    click on the "Track" button. On here, the user can edit details with regards to the product
    and confirm that he/she will add it to his/her wishlist
  * List page, a page where the user can see his/her wishlist
  * Detail page, a page that appears after the user clicks on a specific item on the list page
  * Profile page, where the user can see basic information about themselves such as an email
    and passowrd
    
  #### Back-end
  * Set up Spring
  * Postgres database setup, including the function
  * Full implementation of the User service
    * Four public endpoints, to create users, login users, logout sessions and validate existing
      session tokens
  * Partial implementation of the product services
    * sending out the emails
    * Walmart API
    * Connecting with the database
    
## Technical Highlights

  * When using the Walmart API to look up the price of the products, originally it seemed 
    as though the API wasn't working because no matter which item id (UPC code, web code, etc)
    we queried the API, it always returned an error message saying that the id doesn't exist. 
    After trying a few things we finally realized that is was because the API follows the U.S.A
    walmart website and only recognizes the item ids from the U.S website
  * One challenge was to get Spring read from the environment variables. We decided to export
    sensitive information like the API key as environment variables but we had trouble making
    sure we had all the right annotations for Spring boot to do the job
  * We also found that Spring cannot read environment variables when it is creating a bean.
    Originally, we made the database constructor read the environment variables to get the
    database url etc, but we found that this was not being read. After some research we found that is was 
    because Spring cannot read environemnt variables when it is creating bean. To circumven this,
    we used a separate 'init' function and annotated it with "@PostConstruct" to make sure the 
    set up is done right after the bean creation and before other classes uses them
  * One challenge was setting up the Router feature under React to navigate through different URLs and pages of our application.
    It was the first time for us to work with React and we spend quite some time to figure out the functionalities of it. Routing 
    was the biggest challenge so far because handling all the form submissions and then redirecting to different pages was very
    different from the usual Javascript
    
## Things worked well for Teamwork and process and Things we need to improve on

  * Things that worked well:
  
    * Splitting work between two teams, one for the front-end and the other for the back-end
    * Communicating through Slack
    * Having weekly updates on slack about the progress made by every team member and 
      going over the detailed plan for the following week
    * At least one member from each of the team approving the PR
    
  * Things we need to improve on
  
    * Better communication between the front-end and the back-end team to finish the final phase
      of the project
    * Have better documentation for how to set up both the front-end and the back-end configuration
    
## Triage: Process for the Phase 3

  * For the final phase, the front-end team will finish connecting the front-end to the back-end
  * All team members will assist in cleaning any bugs that we may find in the code
  * The back-end team will finish the implemenation of the batch process
  * The suite of external pricing api's will also be expanded to at least include Best Buy,
    though more may also be included if time permits.
  * The back-end team will also create additional tests for the back-end code
  * We might add the ability for the users to delete their accounts, or for users to change
    their email and the notification settings, if the time permits.
  
