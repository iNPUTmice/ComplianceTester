package eu.siacs.compliance;

import eu.siacs.extensions.csi.ClientStateIndication;
import eu.siacs.compliance.suites.AbstractTestSuite;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.session.Extension;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.session.XmppSessionConfiguration;
import rocks.xmpp.core.session.debug.ConsoleDebugger;

public class TestSuiteFactory {

    public static AbstractTestSuite create(Class <? extends AbstractTestSuite> clazz, Jid jid, String password, boolean debug)
        throws AbstractTestSuite.TestSuiteCreationException {
        XmppSessionConfiguration.Builder builder = XmppSessionConfiguration.builder();
        builder.extensions(Extension.of(ClientStateIndication.class));
        builder.initialPresence(null);
        if (debug) {
            builder.debugger(ConsoleDebugger.class);
        }
        XmppSessionConfiguration configuration = builder.build();
        final XmppClient client = XmppClient.create(jid.getDomain(),configuration);
        try {
            AbstractTestSuite testSuite = clazz.getDeclaredConstructor(XmppClient.class, Jid.class, String.class).newInstance(client, jid, password);
            return testSuite;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AbstractTestSuite.TestSuiteCreationException();
        }
    }
}
