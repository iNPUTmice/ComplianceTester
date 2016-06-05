package eu.siacs.compliance.suites;

import eu.siacs.compliance.tests.*;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;

public class AdvancedServerCore extends AbstractTestSuite {

    public AdvancedServerCore(XmppClient client, Jid jid, String password) {
        super(client, jid, password);
    }

    @Override
    List<Class<? extends AbstractTest>> getTests() {
        return Arrays.asList(
                EntityCapabilities.class,
                PEP.class
        );
    }

    @Override
    public String getName() {
        return "Advanced Server Core Compliance Suite";
    }
}
