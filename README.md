
Running steps:
1. install all maven dependencies
2. tests can be run:
   - manually one by one and the result is immediately visible in the console
   - pokretanjem .XML fajlova gde su testovi grupisani
       testng.xml pokreće sve fajlove
       submitOrderTests.xml pokreće testve koji imaju tag groups = {"SubmitOrder"}
       ErrorValidationsTest.xml pokreće testve koji imaju tag groups = {"ErrorHandling"}

In progress of production:
- Running tests from the command line
- Provision of test reports with screenshots for failed tests