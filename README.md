
Running steps:
1. install all Maven dependencies
2. tests can be run:
   - manually one by one and the result is immediately visible in the console
   - open the terminal in the folder where the pom.xml file is located and run the command **mvn test -P<profile name>**
   We have three profiles:
   - ErrorValidation
   - SubmitOrder
   -  Regression
3. after the execution of tests index.html report is generated in the "reports" folder. 
index.xml report contains screenshots for tests that failed.


