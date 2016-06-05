package eu.siacs.compliance.suites;

import eu.siacs.compliance.tests.*;
import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;


/**
 * this test suite just tests for everything
 */
public class EverythingTestSuite extends AbstractTestSuite {

    public EverythingTestSuite(XmppClient client) {
        super(client);
    }

    @Override
    List<Class<? extends AbstractTest>> getTests() {
        return Arrays.asList(
                RosterVersioning.class,
                StreamManagement.class,
                CSI.class,
                Blocking.class,
                MAM.class,
                Push.class,
                EntityCapabilities.class,
                MultiUserChat.class,
                HttpUpload.class,
                PEP.class
        );
    }

    @Override
    public String getName() {
        return "Relevant for Conversations";
    }
}
