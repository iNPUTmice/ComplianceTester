package eu.siacs.compliance;

import eu.siacs.compliance.tests.AbstractTest;
import rocks.xmpp.core.session.XmppClient;

public class TestFactory {

    public static AbstractTest create(Class <? extends AbstractTest> clazz, XmppClient client) throws TestCreationException {
        if (client == null) {
            throw new TestCreationException();
        }
        try {
            AbstractTest test = clazz.getDeclaredConstructor(XmppClient.class).newInstance(client);
            return test;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TestCreationException();
        }
    }

    public static class TestCreationException extends Exception {

    }
}
