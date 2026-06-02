package utilities;

/**
 * @author Ajay Talpur
 */
public class ScenarioResult {
    private String scenarioName;
    private String scenarioStatus;

    public ScenarioResult(String scenarioName, String scenarioStatus) {
        this.scenarioName = scenarioName;
        this.scenarioStatus = scenarioStatus;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public String getScenarioStatus() {
        return scenarioStatus;
    }
}
