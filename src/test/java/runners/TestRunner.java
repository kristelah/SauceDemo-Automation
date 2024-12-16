//This class:
//Specifies where the feature files are located (features path).
//Points to the step definitions package (glue path).
//Configures Cucumber to use TestNG as the test runner.

package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/LoginAndCheckout.feature",  // path to the feature file
        glue = {"stepdefinitions"}, // package where step definitions are present
        plugin = {"pretty", "html:target/cucumber-reports.html"} // generates HTML report
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
