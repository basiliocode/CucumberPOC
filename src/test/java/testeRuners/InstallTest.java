package testeRuners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features_lib",
        tags = "@installTest",
        glue = "steps",
        monochrome = true
)
public class InstallTest {
}
