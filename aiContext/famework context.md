# ai-test-framework AI Framework Context

## Framework Name

ai-test-framework

A Selenium + Java + Cucumber + TestNG automation framework designed for parallel execution, reusable page objects, centralized actions, JSON-driven test data management, and detailed execution reporting.

---

## Technology Stack

Language:

* Java 21

Automation:

* Selenium WebDriver 4

BDD:

* Cucumber

Execution Engine:

* TestNG

Build Tool:

* Maven

Reporting:

* Cucumber JSON Reports
* Masterthought Reports
* Custom Execution Summary

Logging:

* Cucumber Scenario Attachments

Test Data:

* JSON Files
* Jackson Object Mapping

Supported Execution:

* Local
* BrowserStack (planned)

---

## Framework Architecture

The framework follows a layered architecture.

Feature Files
↓
Step Definitions
↓
Page Objects
↓
Reusable Components
↓
Driver Actions
↓
Selenium WebDriver

---

## Project Structure

src/main/java

assertions
driverConfig
enums
hooks
utilities

src/test/java

runners
web

src/test/resources

features
testData

---

## Package Responsibilities

### assertions

Contains assertion utilities.

Current class:

AssertUtils

Purpose:

* Centralized assertions
* Consistent validation messages
* Reusable verification methods

AI Rule:

Never place TestNG assertions directly inside step definitions.

Use AssertUtils.

---

### driverConfig

Contains driver management and reusable browser actions.

Current classes:

Actions
RuntimeConfig
WebDriverFactory

Responsibilities:

WebDriverFactory

* Creates browser instances
* Maintains ThreadLocal WebDriver
* Supports parallel execution
* Handles browser initialization

Actions

Centralized Selenium actions.

Examples:

* click()
* type()
* getText()
* waitTillClickable()
* waitTillVisible()
* waitUntilTextToDisplay()
* alert handling

AI Rules:

Never use:

driver.findElement()

inside page objects.

Always use:

Actions.click()
Actions.type()
Actions.getText()

Never use:

Thread.sleep()

Always use existing wait methods.

---

### enums

Contains framework constants.

Current:

Timeouts

Purpose:

Centralized timeout values.

AI Rule:

Reuse existing timeout enums.

Do not hardcode wait durations.

---

### hooks

Contains Cucumber hooks.

Current:

Hooks

Responsibilities:

@Before

* Create WebDriver
* Launch application
* Maximize browser

@After

* Capture scenario result
* Update execution summary
* Quit driver

AI Rule:

Never create new driver initialization logic.

Always rely on existing hooks.

---

### utilities

Contains framework utilities.

Current:

ConfigReader
ExecutionSummary
Reporter
ScenarioManager
ScenarioResult
Util

Responsibilities:

ConfigReader

Loads property files.

Reporter

Writes logs to Cucumber reports.

ScenarioManager

Stores current scenario using ThreadLocal.

ExecutionSummary

Stores:

* Passed count
* Failed count
* Scenario status

AI Rules:

Use Reporter.log() for execution messages.

Do not use System.out.println inside test code.

Use ScenarioManager where scenario access is required.

---

## Runner Layer

Runner

Responsibilities:

* Executes feature files
* Uses CucumberOptions
* Parallel execution enabled

ExecutionListener

Responsibilities:

* Execution start logging
* Execution end logging
* Scenario summary generation
* Masterthought report generation

AI Rule:

Do not create additional runner classes unless requested.

---

## Test Data Architecture

Location:

src/test/resources/testData

Examples:

address.json
cards.json
products.json

Data Access Layer:

TestDataManager

Capabilities:

* JSON caching
* Object mapping
* Primitive value retrieval
* Nested path access

Wrappers:

AddressData
CardData
ProductData

POJOs:

Address
Cards
Products

AI Rules:

Never hardcode test data.

Use:

TestDataManager.getValue()

or wrapper classes.

Example:

String user =
TestDataManager.getValue(
"users",
"validUser",
"username"
);

Preferred Approach:

Wrapper Class
↓
POJO
↓
TestDataManager

---

## Context Management

Classes:

ContextManager
TestDataContext

Purpose:

Store test execution data.

Examples:

* username
* password
* runtime values

AI Rule:

For sharing data between steps:

Use ContextManager

Do not create static variables.

---

## Page Object Layer

Current Pages:

HomePage
SignInPage
SignUpPage

Responsibilities:

Business actions only.

Examples:

userLogIn()
validateLoggedInUser()
logOut()

AI Rules:

Page objects must:

* Contain business methods
* Hide locators
* Hide selenium implementation

Good:

homePage.userLogIn()

Bad:

step definition directly clicking locators

---

## Component Layer

Current Components:

NavBar
LogInPanel
SignUpPanel

Purpose:

Reusable UI fragments.

Examples:

Navigation menu
Login modal
Signup modal

AI Rules:

Reusable sections must become Components.

Do not duplicate locators across pages.

If a section appears in multiple pages:

Create Component.

---

## Step Definition Layer

Current:

HomePageSteps
LoginSteps
SignUpPageSteps

Responsibilities:

Map Gherkin to business actions.

AI Rules:

Step Definitions should:

* Be thin
* Call page methods
* Not contain Selenium code
* Not contain locators
* Not contain waits

Good:

homePage.userLogIn()

Bad:

driver.findElement()

---

## Reporting Standards

Use:

Reporter.log()

Examples:

Reporter.log(
"Successfully Logged in"
);

Do not use:

System.out.println()

inside steps or pages.

---

## Locator Strategy

Preferred Order:

id
name
css
xpath

Avoid fragile xpath whenever possible.

Locators must remain private inside:

Pages
Components

Never expose locators to Step Definitions.

---

## Parallel Execution Requirements

Framework uses:

ThreadLocal WebDriver

Framework supports:

Parallel scenario execution

AI Rules:

Never create static WebDriver.

Never store WebDriver in page objects.

Always access driver through:

WebDriverFactory.getDriver()

---

## Coding Standards

Every Java file must include:

@author Ajay Talpur

Class Rules:

* Single responsibility
* Small reusable methods
* No duplicate logic
* Follow existing package structure

Exception Handling:

Use framework exceptions.

Provide meaningful messages.

---

## AI Output Requirements

When generating automation from manual test cases:

Generate:

1. Feature File
2. Step Definition Class
3. Page Object
4. Component Classes if required
5. Test Data Updates if required

Must Follow:

* Existing package structure
* Existing naming conventions
* Existing Actions class
* Existing Reporter class
* Existing TestDataManager architecture
* Existing ContextManager architecture

Never Generate:

* Raw Selenium code in steps
* New driver setup code
* New reporting framework
* New assertion framework
* Thread.sleep()

Always Generate Code Compatible With ai-test-framework.


Important Rules:
1. Reuse existing classes, utilities, managers, wrappers, page objects, hooks, listeners, configurations, and helper methods wherever possible.
2. Do not create duplicate functionality.
3. Create new code only when the required functionality does not already exist.
4. Maintain consistency with the current framework architecture, package structure, naming conventions, and coding style.
5. Prefer extending existing components over introducing new ones.
6. Ensure all new code is robust, scalable, maintainable, and production-ready.
7. Follow SOLID principles, clean code practices, and design patterns already used in the framework.
8. Implement proper logging, exception handling, and validation.
9. Avoid hardcoded values; use configuration or reusable constants.
10. Before generating code, explain:
    - Which existing components were analyzed
    - Which components can be reused
    - Why any new component is required

The final solution should minimize code duplication and maximize framework reusability.
