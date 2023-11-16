package marko.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/marko/Cucumber", glue="marko.StepDefinitions", monochrome = true, plugin={"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
//tags = "@Regression", if we want run only Regression tests