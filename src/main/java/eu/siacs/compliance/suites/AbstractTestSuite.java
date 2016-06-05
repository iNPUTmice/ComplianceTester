package eu.siacs.compliance.suites;

import eu.siacs.compliance.tests.AbstractTest;
import eu.siacs.compliance.Result;
import eu.siacs.compliance.tests.TestFactory;
import rocks.xmpp.core.session.XmppClient;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractTestSuite {

    private final XmppClient mXmppClient;

    private HashMap<Class<?extends AbstractTest>,Result> mTestResults = new HashMap<>();

    public AbstractTestSuite(XmppClient client) {
        mXmppClient = client;
    }

    public void run() {
        for(Class<? extends AbstractTest> test : getTests()) {
            run(test);
        }
        int passed = Collections.frequency(mTestResults.values(),Result.PASSED);
        System.out.println("passed "+passed+"/"+mTestResults.size());
    }

    public Result result() {
        return Collections.frequency(mTestResults.values(),Result.FAILED) == 0 ? Result.PASSED : Result.FAILED;
    }

    private void run(Class<? extends AbstractTest> clazz) {
        try {
            AbstractTest test = TestFactory.create(clazz,mXmppClient);
            System.out.print("running "+test.getName()+"…");
            Result result = test.run();
            System.out.println("\t\t"+result);
            mTestResults.put(clazz, test.run());
        } catch (TestFactory.TestCreationException e) {
           System.err.println("failed to create test for class "+clazz.toString());
            mTestResults.put(clazz, Result.FAILED);
        }
    }

    abstract List<Class<?extends AbstractTest>> getTests();

    public abstract String getName();
    
    public static class TestSuiteCreationException extends Exception {

    }
}
