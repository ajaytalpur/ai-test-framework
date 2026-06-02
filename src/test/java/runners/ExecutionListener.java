package runners;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.testng.IExecutionListener;
import utilities.ExecutionSummary;
import utilities.ScenarioResult;
import utilities.Util;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * @author Ajay Talpur
 */
public class ExecutionListener implements IExecutionListener {

    @Override
    public void onExecutionStart() {
        System.out.println("****** Test Execution Starts *******");
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("****** Test Execution End *******");
        System.out.println(ExecutionSummary.getPassed());
        System.out.println(ExecutionSummary.getFailed());
        for(ScenarioResult result : ExecutionSummary.getResults()){
            System.out.println("****** Scenario  ******* "+result.getScenarioName());
            System.out.println("****** Status  ******* "+result.getScenarioStatus());
        }

        File reportOutputDirectory = new File("executionReports/Report_" + Util.getTimeStamp());
        List<String> jsonFiles = Collections.singletonList("target/cucumber/cucumber.json");
        Configuration configuration = new Configuration(reportOutputDirectory, "sampProject");
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
