import org.testng.ITestResult;
import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

public class ReadableListener extends TestListenerAdapter {

    public final static String PASS = "Pass";
    public final static String FAIL = "Fail";
    public final static String SKIP = "Skip";

    private StringBuilder output;

    @Override
    public void onStart(ITestContext testContext)
    {
        output = new StringBuilder();
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        log(ReadableListener.FAIL, testResult);
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        log(ReadableListener.SKIP, testResult);
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        log(ReadableListener.PASS, testResult);
    }

    public void log(String status, ITestResult testResult)
    {
        String description = testResult.getMethod().getDescription();
        if (description == null) {
            description = testResult.getName();
        }
        output.append(status + ": " + description + "\n");
    }

    @Override
    public void onFinish(ITestContext testContext)
    {
        System.out.println(testContext.getName());
        System.out.println("-----------------------------------------------");
        System.out.print(output.toString());
        System.out.println("-----------------------------------------------\n");
    }
}