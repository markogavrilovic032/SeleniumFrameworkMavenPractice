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
1. install all Maven dependencies
2. tests can be run:
   - manually one by one and the result is immediately visible in the console
   - open the terminal in the folder where the pom.xml file is located and run the command **mvn test -Pprofile_name**
   
   We have three profiles:
   - ErrorValidation
   - SubmitOrder
   -  Regression
3. after the execution of tests index.html report is generated in the "reports" folder. 
index.xml report contains screenshots of tests that failed.
