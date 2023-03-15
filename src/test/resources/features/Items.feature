Feature: Item purchase

  
 Scenario: Add Item to cart
  
    Given Login Into App
    
    When Add Item to cart
    	|item|
    	| Nexus 6 |
			| Sony xperia z5 | 
			| Nokia lumia 1520 |
       
    Then Item must added to the cart
    

	Scenario: Delete an Item
#		Given Login Into App
		When  List of items available in the cart
		Then  Delete an item from cart
		

	Scenario: place order
			When Items should be available in the cart
			Then Place Order with address
			And purchase items selected
		
		
