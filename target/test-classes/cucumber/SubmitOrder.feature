
@tag
	Feature: Purchase orderdrom Ecom Website
	I want to use this template for y feature file
	
	
	Background:
Given I landed on Ecom page
	
	@Regression
	Scenario Outline:Poositive: Test for Submitting the order
		Given Logged in with username <name> and password<password>
		When I add product <productName> to car
		And checkout <prductName> and submit the order to <countryName>
		Then "THANKYOU FOR THE ORDER." meesage is displayed on confimation page
		
		Examples:
		|name                    |     password|     productName|	countryName |
		|lokithor@gmail.com      |    Mmpl@2025|     ZARA COAT 3|	India		|

