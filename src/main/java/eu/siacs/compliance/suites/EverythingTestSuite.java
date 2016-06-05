package eu.siacs.compliance.suites;

import eu.siacs.compliance.tests.*;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;


/**
 * this test suite just tests for everything
 */
public class EverythingTestSuite extends AbstractTestSuite {

    public EverythingTestSuite(XmppClient client, Jid jid, String password) {
        super(client, jid, password);
    }

    @Override
    List<Class<? extends AbstractTest>> getTests() {
        return Arrays.asList(
                RosterVersioning.class,
                StreamManagement.class,
                MessageCarbons.class,
                Blocking.class,
                MAM.class,
                Push.class,
                EntityCapabilities.class,
                MultiUserChat.class,
                HttpUpload.class,
                PEP.class,
                CSI.class,
                Proxy65.class
        );
    }

    @Override
    public String getName() {
        return "Relevant for Conversations";
    }
}
