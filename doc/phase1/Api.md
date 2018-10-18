# API endpoints

## GET
  * */users/**userid*** - User based on userid and password
  * */users/**userid**/tracking/**product id*** - Sale information of the item in the wishlist
  * */users/**userid**/tracking* - view all the Wishlist items

## PUT:
  * */users/* - New user
  * */users/**userid**/tracking* - New wishlist
  * */users/**userid**/tracking/**productid*** - Track the product <productid>
  * */users/**userid*** - send email/notify user 

## POST: (update)
  * */users/**userid**/tracking/**product id*** - Item in the wishlist
  * */users/**userid*** - update the user info

## DELETE:
  * */users/**userid**/tracking/**productid*** -  Item from wishlist
  * */users/**userid*** - User
