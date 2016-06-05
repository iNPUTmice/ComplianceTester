package eu.siacs;

import eu.siacs.compliance.suites.AbstractTestSuite;
import eu.siacs.compliance.suites.EverythingTestSuite;
import eu.siacs.compliance.suites.TestSuiteFactory;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.sasl.AuthenticationException;

public class ComplianceTester {


    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("java -jar ComplianceTester.jar username@domain.tld password");
            System.exit(1);
        }
        Jid jid = Jid.of(args[0]);
        String password = args[1];
        runTestSuite(EverythingTestSuite.class, jid, password);
    }

    private static void runTestSuite(Class <? extends AbstractTestSuite> clazz, Jid jid, String password) {
         try {
            try {
                AbstractTestSuite testSuite = TestSuiteFactory.create(clazz, jid, password);
                System.out.println("Use compliance suite '"+testSuite.getName()+"' to test "+jid.getDomain()+"\n");
                testSuite.run();
                System.out.println("\n"+testSuite.getName() + ": " + testSuite.result());
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
