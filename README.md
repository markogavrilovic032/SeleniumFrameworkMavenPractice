# SeleniumFrameworkMavenPractice

## Installation & Prerequisites
1. JDK 1.8+ (Ensure that the Java class path is properly set)
2. Maven (Ensure that the .m2 class path is properly set)
3. Eclipse IDE 
4. Required Eclipse Plugins:
   - Maven
  
## Framework Setup
To set up the framework, you can either fork or clone the repository from [here](https://github.com/markogavrilovic032/SeleniumFrameworkMavenPractice/tree/master) or download the ZIP file and set it up in your local workspace.

## Running steps:
Open the terminal in the folder where the pom.xml file is located and run the command **mvn test -Pprofile_name -Dbrowser=browser_name**
   
We have three profiles:
  - ErrorValidation
  - SubmitOrder 
  - Regression
Test can be run in three browsers:
  - chrome
  - chromeheadless -> is chrome browser without a graphical user interface
  - firefox

Example command for running regression tests in chrome browser
**mvn test -PRegression -Dbrowser=chrome**

## Note:
A Cucumber example with feature and stepDefinition files is also written. Cucumber tests are run with this command
** mvn test -PCucumberTests -Dbrowser=chrome**

## Test repors
After the execution of tests index.html report is generated in the "reports" folder. 
index.xml report contains screenshots of tests that failed.

## Notes:
If a test has failed, it will be automatically started again, and the first execution of that test will have the status of **skipped**
