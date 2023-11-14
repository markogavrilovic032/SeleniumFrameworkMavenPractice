
Running steps:
1. install all Maven dependencies
2. tests can be run:
   - manually one by one and the result is immediately visible in the console
   - by running .xml files where the tests are grouped
       - testng.xml runs all files
       - submitOrderTests.xml runs tests that have tag groups = {"SubmitOrder"}
       - ErrorValidationsTest.xml runs tests that have tag groups = {"ErrorHandling"}
3. after the execution of tests via .xml files, index.html report is generated in the "reports" folder. 
index.xml report contains screenshots for tests that failed.

In progress of production:
- Running tests from the command line

