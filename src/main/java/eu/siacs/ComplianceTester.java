package eu.siacs;

import eu.siacs.compliance.suites.AbstractTestSuite;
import eu.siacs.compliance.suites.EverythingTestSuite;
import eu.siacs.compliance.suites.TestSuiteFactory;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.sasl.AuthenticationException;
import rocks.xmpp.core.session.XmppClient;

public class ComplianceTester {


    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("java -jar ComplianceTester.jar username@domain.tld password");
            System.exit(1);
        }
        Jid jid = Jid.of(args[0]);
        String password = args[1];
        XmppClient xmppClient = XmppClient.create(jid.getDomain());
        try {
            xmppClient.connect();
            xmppClient.login(jid.getLocal(), password);
            try {
                AbstractTestSuite testSuite = TestSuiteFactory.create(EverythingTestSuite.class, xmppClient);
                testSuite.run();
                System.out.println(testSuite.getName() + ": " + testSuite.result());
            } catch (AbstractTestSuite.TestSuiteCreationException e) {
                e.printStackTrace();
                System.out.println("Test suite creation failed");
            }
        } catch(AuthenticationException e) {
            System.err.println("username or password wrong");
            System.exit(1);
        } catch (XmppException e) {
            e.printStackTrace();
        }
    }
}
