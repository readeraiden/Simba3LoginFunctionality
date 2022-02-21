@wip
Feature: Login as a different user with valid credentials

  @INTA-903 @INTA-896
  Scenario Outline: Login with valid <UserType>
    Given User on the Login Page
    When User enters valid credentials for "<UserType>"
    And User clicks Login Button
    Then User should be able to login and "<Subtitle>" should be displayed
    Examples:
      | UserType      | Subtitle        |
      | Driver        | Quick Launchpad |
      | Sales Manager | Dashboard       |
      | Store Manager | Dashboard       |

  @INTA-989
  Scenario Outline: login as a given user <user>
    Given User on the Login Page
    When the user logs in using following credentials
      | username  | <user>      |
      | password  | UserUser123 |
      | firstname | <firstName> |
      | lastname  | <lastName>  |

    Examples:
      | user            | firstName | lastName  |
      | user10          | Brenden   | Schneider |
      | salesmanager101 | Liza      | Fritsch   |
      | storemanager85  | Stephan   | Haley     |


  @INTA-990
  Scenario: (Negative) Driver should land on the "Quick Launchpad" page after successful login
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

  @INTA-914
  Scenario Outline: Menu Options Sales Manager
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

  @INTA-991
  Scenario: the user should NOT get access the application without providing credentials
    Given the user logged in as "store manager"
    When the user get the current url
    And the user logs out
    Then the user paste url and should not get in

  @INTA-992
  Scenario: the user should NOT get in after closing the Browser without logging out
    Given the user logged in as "store manager"
    When the user get the current url
    Then the user paste url and should not get in

  @INTA-993
  Scenario: the user should get in after closing the TAB without logging out
    Given the user logged in as "store manager"
    When the user get the current url
    Then open a new tab and close the old one and paste the url and get in

  @INTA-994
  Scenario: Leading and trailing spaces entered into the Username field are trimmed
    Given User on the Login Page
    When the user logs in with "   storemanager51   " and "UserUser123"
    And User clicks Login Button
    Then the user should able to login to "Dashboard" page

  @INTA-995
  Scenario: All the fields in the Login page have the proper placeholders (Username or Email and Password)
    Given User on the Login Page
    When input box has "Username or Email" and password box has "Password" as placeholder

  @INTA-906
  Scenario Outline:Either invalid credentials or empty fields give warning message
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

  @INTA-996
  Scenario: the Password text entered into the 'Password' field is toggled to hide its visibility
    Given User on the Login Page
    When the user logs in with "storemanager51" and "UserUser123"
    Then the password box should hide the text with dots

  @INTA-997
  Scenario: the Password is not visible in the Page Source
    Given User on the Login Page
    When the user logs in with "storemanager51" and "UserUser123"
    Then the Password is not visible in the Page Source

  @INTA-998
  Scenario: the copying of the text entered into the Password field (It should not be applicable)
    Given User on the Login Page
    When the user enters password "UserUser123"
    Then the Password can not be copied

  @INTA-999
  Scenario: User lands on the ‘Forgot Password’ page after clicking on the "Forgot your password?" link
    Given User on the Login Page
    When the user clicks on the Forgot your password link
    Then the user should navigate to "Forgot Password" page

  @INTA-1000
  Scenario: Users should change their password with the "REQUEST" button
    Given User on the Login Page
    When the user clicks on the Forgot your password link
    And the user enter username "user11" into the box and click Request button
    Then the user should be able to change the password

  @INTA-1001
  Scenario: "Remember me on this computer" link should be clickable
    Given User on the Login Page
    Then Remember me on this computer link is clickable

  @INTA-1002
  Scenario:User login with using "Enter" key
    Given User on the Login Page
    When the user enters username "storemanager51" and hit the Enter key
    When the user enters password "UserUser123" and hit the Enter key
    Then the user should able to login to "Dashboard" page

  @INTA-1003
  Scenario: User login with using "TAB" key
    Given User on the Login Page
    When the user enters username "storemanager51" and hit the Tab key
    When the user enters password "UserUser123" and hit the Enter key
    Then the user should able to login to "Dashboard" page

  @INTA-1004
  Scenario:The background color of "LOG IN" button (hex code #0c84a3)
    Given User on the Login Page
    Then verify hex code of the background color of the log in button is "#0c84a3"