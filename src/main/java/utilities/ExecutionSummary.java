package utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ajay Talpur
 */
public class ExecutionSummary {

    private static final AtomicInteger passed =
            new AtomicInteger();

    private static final AtomicInteger failed =
            new AtomicInteger();

    private static final List<ScenarioResult> results = new CopyOnWriteArrayList<>();

    public static void addResult(ScenarioResult result) {
        results.add(result);
    }

    public static List<ScenarioResult> getResults() {
        return results;
    }

    public static void incrementPassed() {
        passed.incrementAndGet();

    }

    public static void incrementFailed() {
        failed.incrementAndGet();
    }

    public static int getPassed() {
        return passed.get();
    }

    public static int getFailed() {
        return failed.get();
    }
}