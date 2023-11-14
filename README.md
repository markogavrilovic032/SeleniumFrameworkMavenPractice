
Running steps:
1. install all Maven dependencies
2. tests can be run:
   - manually one by one and the result is immediately visible in the console
   - by running .xml files where the tests are grouped
       - testng.xml runs all files
       - submitOrderTests.xml runs tests that have tag groups = {"SubmitOrder"}
       - ErrorValidationsTest.xml runs tests that have tag groups = {"ErrorHandling"}

In progress of production:
- Running tests from the command line
- Provision of test reports with screenshots of failed tests
