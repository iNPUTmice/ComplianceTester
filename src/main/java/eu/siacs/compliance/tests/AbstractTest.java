package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import rocks.xmpp.core.session.XmppClient;

public abstract class AbstractTest {

    protected final XmppClient client;

    public AbstractTest(XmppClient client) {
        this.client = client;
    }


    public abstract Result run();

    public abstract String getName();
}
