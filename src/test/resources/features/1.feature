@INTA-1030
Feature: Default


	@INTA-903 @INTA-896
	Scenario Outline: US01_AC01_TC01 Login with valid credentials(Aiden R)
		Login with valid <UserType>
		Given User on the Login Page
		When User enters valid credentials for "<UserType>"
		And User clicks Login Button
		Then User should be able to login and "<Subtitle>" should be displayed
		Examples:
		|UserType     |Subtitle         |
		|Driver       |Quick Launchpad  |
		|Sales Manager|Dashboard        |
		|Store Manager|Dashboard        |	


	@INTA-906 @INTA-896
	Scenario Outline: US01_AC06_TC01 User should NOT able to login with invalid credentials(Aiden R)
		Either invalid credentials or empty fields give warning message
		    Given User on the Login Page
		    When the user enters invalid or "<username>" and "<password>"
		    And User clicks Login Button
		    Then warning "<message>" should be displayed
		    Examples:
		      | username | password    | message                        |
		      | user11   | wrong       | Invalid user name or password. |
		      | wrong    | UserUser123 | Invalid user name or password. |
		      | wrong    | wrong       | Invalid user name or password. |
		      |          | UserUser123 | Please fill out this field.    |
		      | user11   |             | Please fill out this field.    |
		      |          |             | Please fill out this field.    |	


	Scenario Outline: US01_AC01_TC02 Login with different credentials  - Dashboard Page Appearance(Aiden R)
		Menu Options Sales Manager
		    Given the user logged in as "store manager"
		    Then the user should see following modules
		      | Dashboards         |
		      | Fleet              |
		      | Customers          |
		      | Sales              |
		      | Activities         |
		      | Marketing          |
		      | Reports & Segments |
		      | System             |
		    Then verify the "<Breadcrumb>", "<Page Subtitle>" "<Page URL>", "<Page Title>"
		    Examples:
		      | Breadcrumb            | Page Subtitle | Page URL                  | Page Title |
		      | Dashboards/ Dashboard | Dashboard     | https://qa.intabella.com/ | Dashboard  |	


	@INTA-989 @INTA-896
	Scenario Outline: US01_AC01_TC03 Login with different users(Aiden R)
		login as a given user <user>
		    Given User on the Login Page
		    When the user logs in using following credentials
		      | username  | <user>      |
		      | password  | UserUser123 |
		      | firstname | <firstName> |
		      | lastname  | <lastName>  |
		
		    Examples:
		      | user           | firstName | lastName  |
		      | user10         | Brenden   | Schneider |
		      | salesmanager101| Liza      | Fritsch   |
		      | storemanager85 | Stephan   | Haley     |	


	@INTA-990 @INTA-896
	Scenario: US01_AC01_TC04 (Negative) Driver should land on the "Quick Launchpad" page after successful login(Aiden R)
		(Negative) Driver should land on the "Quick Launchpad" page after successful login
		      Given User on the Login Page
		      When the user logs in with "user1" and "UserUser123"
		      And User clicks Login Button
		      Then the user navigates to "Activities" "Calendar Events"
		      And the user get the current url
		      And the user logs out
		      Then the user paste url
		      And the user logs in with "user10" and "UserUser123"
		      And User clicks Login Button
		      Then the user should able to login to "Quick Launchpad" page	


	@INTA-991 @INTA-896
	Scenario: US01_AC02_TC01 User should NOT get access the application without providing credentials(Aiden R)
		the user should NOT get access the application without providing credentials
		    Given the user logged in as "store manager"
		    When the user get the current url
		    And the user logs out
		    Then the user paste url and should not get in	


	@INTA-992 @INTA-896
	Scenario: US01_AC03_TC01 User should NOT get in after closing the Browser without logging out(Aiden R)
		the user should NOT get in after closing the Browser without logging out
		    Given the user logged in as "store manager"
		    When the user get the current url
		    Then the user paste url and should not get in	


	@INTA-993 @INTA-896
	Scenario: US01_AC03_TC02 User should get in after closing the TAB without logging out(Aiden R)
		the user should get in after closing the TAB without logging out
		      Given the user logged in as "store manager"
		      When the user get the current url
		      Then open a new tab and close the old one and paste the url and get in	


	@INTA-994 @INTA-896
	Scenario: US01_AC04_TC01 Validate whether the leading and trailing spaces entered into the Username field are trimmed(Aiden R)
		Leading and trailing spaces entered into the Username field are trimmed
		    Given User on the Login Page
		    When the user logs in with "   storemanager51   " and "UserUser123"
		    And User clicks Login Button
		    Then the user should able to login to "Dashboard" page	


	@INTA-995 @INTA-896
	Scenario: US01_AC05_TC01 Validate all the fields in the Login page have the proper placeholders ** (Username or Email and Password)(Aiden R)
		All the fields in the Login page have the proper placeholders (Username or Email and Password)
		    Given User on the Login Page
		    When input box has "Username or Email" and password box has "Password" as placeholder	


	@INTA-996 @INTA-896
	Scenario: US01_AC07_TC01 Validate the Password text entered into the 'Password' field is toggled to hide its visibility(Aiden R)
		the Password text entered into the 'Password' field is toggled to hide its visibility
		    Given User on the Login Page
		    When the user logs in with "storemanager51" and "UserUser123"
		    Then the password box should hide the text with dots	


	@INTA-997 @INTA-896
	Scenario: US01_AC08_TC01 Validate the Password is not visible in the Page Source(Aiden R)
		the Password is not visible in the Page Source
		    Given User on the Login Page
		    When the user logs in with "storemanager51" and "UserUser123"
		    Then the Password is not visible in the Page Source	


	@INTA-998 @INTA-896
	Scenario: US01_AC09_TC01 Validate the copying of the text entered into the Password field(Aiden R)
		the copying of the text entered into the Password field (It should not be applicable)
		    Given User on the Login Page
		    When the user enters password "UserUser123"
		    Then the Password can not be copied	


	@INTA-999 @INTA-896
	Scenario: US01_AC10_TC01 User lands on the ‘Forgot Password’ page after clicking on the "Forgot your password?" link(Aiden R)
		User lands on the ‘Forgot Password’ page after clicking on the "Forgot your password?" link
		    Given User on the Login Page
		    When the user clicks on the Forgot your password link
		    Then the user should navigate to "Forgot Password" page	


	@INTA-1000 @INTA-896
	Scenario: US01_AC10_TC02 Users should change their password with the "REQUEST" button(Aiden R)
		Users should change their password with the "REQUEST" button
		    Given User on the Login Page
		    When the user clicks on the Forgot your password link
		    And the user enter username "user11" into the box and click Request button
		    Then the user should be able to change the password	


	@INTA-1001 @INTA-896
	Scenario: US01_AC11_TC01 Validate user can see the "Remember me on this computer" link on the login page and it should be clickable(Aiden R)
		"Remember me on this computer" link should be clickable
		    Given User on the Login Page
		    Then Remember me on this computer link is clickable	


	@INTA-1002 @INTA-896
	Scenario: US01_AC12_TC01 Validate to login by using the Keyboard "ENTER" key(Aiden R)
		User login with using "Enter" key
		    Given User on the Login Page
		    When the user enters username "storemanager51" and hit the Enter key
		    When the user enters password "UserUser123" and hit the Enter key
		    Then the user should able to login to "Dashboard" page	


	@INTA-1003 @INTA-896
	Scenario: US01_AC12_TC02 Validate to login by using the Keyboard "TAB" key(Aiden R)
		User login with using "TAB" key
		      Given User on the Login Page
		      When the user enters username "storemanager51" and hit the Tab key
		      When the user enters password "UserUser123" and hit the Enter key
		      Then the user should able to login to "Dashboard" page	


	@INTA-1004 @INTA-896
	Scenario: US01_AC13_TC01 Validate background color  of "LOG IN"  button (hex code #0c84a3)(Aiden R)
		The background color of "LOG IN" button (hex code #0c84a3)
		    Given User on the Login Page
		    Then verify hex code of the background color of the log in button is "#0c84a3"