package web.dataManager.context;

/**
 * @author Ajay Talpur
 */
public class ContextManager {

    private static final ThreadLocal<TestDataContext> testDataContextThreadLocal =
            ThreadLocal.withInitial(TestDataContext::new);


    public static TestDataContext getContext() {
        return testDataContextThreadLocal.get();
    }

    public static void removeContext() {
        testDataContextThreadLocal.remove();
    }


}
