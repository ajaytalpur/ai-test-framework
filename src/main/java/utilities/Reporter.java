package utilities;

/**
 * @author Ajay Talpur
 */
public class Reporter {

    public static void log(String message) {
        ScenarioManager.getScenario().attach(message,
                "text/plain",
                "Log");
    }
}
