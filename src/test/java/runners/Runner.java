package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

/**
 * @author Ajay Talpur
 */
@Listeners(ExecutionListener.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"web.steps", "hooks"},
        monochrome = true,
        plugin = {"pretty", "json:target/cucumber/cucumber.json"},
        tags = "@webSamp"
)
public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}