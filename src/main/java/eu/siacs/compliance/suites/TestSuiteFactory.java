package eu.siacs.compliance.suites;

import rocks.xmpp.core.session.XmppClient;

public class TestSuiteFactory {

    public static AbstractTestSuite create(Class <? extends AbstractTestSuite> clazz, XmppClient client) throws AbstractTestSuite.TestSuiteCreationException {
        if (client == null) {
            throw new AbstractTestSuite.TestSuiteCreationException();
        }
        try {
            AbstractTestSuite testSuite = clazz.getDeclaredConstructor(XmppClient.class).newInstance(client);
            return testSuite;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AbstractTestSuite.TestSuiteCreationException();
        }
    }
}
