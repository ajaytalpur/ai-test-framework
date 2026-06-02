package hooks;

import driverConfig.RuntimeConfig;
import driverConfig.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ExecutionSummary;
import utilities.ScenarioManager;
import utilities.ScenarioResult;

/**
 * @author Ajay Talpur
 */
public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        ScenarioManager.setScenario(scenario);
        WebDriverFactory.createDriver();
        WebDriverFactory.getDriver().get(RuntimeConfig.getURL());
        WebDriverFactory.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        Scenario scenario = ScenarioManager.getScenario();
        if (scenario.isFailed()) {
            ExecutionSummary.incrementFailed();
        } else {
            ExecutionSummary.incrementPassed();
        }
        ExecutionSummary.addResult(new ScenarioResult(scenario.getName(),
                scenario.getStatus().toString()));
        ScenarioManager.removeScenario();
        WebDriverFactory.quitDriver();
    }
}
