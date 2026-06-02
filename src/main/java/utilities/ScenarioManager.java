package utilities;

import io.cucumber.java.Scenario;

/**
 * @author Ajay Talpur
 */
public class ScenarioManager {

    private static ThreadLocal<Scenario> scenario = new ThreadLocal();


    public static Scenario getScenario() {
        return scenario.get();
    }

    public static void setScenario(Scenario scenario) {
        ScenarioManager.scenario.set(scenario);
    }

    public static void removeScenario() {
        scenario.remove();
    }


}
