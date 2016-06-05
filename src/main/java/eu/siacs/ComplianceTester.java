package eu.siacs;

import eu.siacs.compliance.suites.*;
import eu.siacs.compliance.TestSuiteFactory;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.sasl.AuthenticationException;

import java.util.Arrays;
import java.util.List;

public class ComplianceTester {


    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("java -jar ComplianceTester.jar username@domain.tld password");
            System.exit(1);
        }
        Jid jid = Jid.of(args[0]);
        String password = args[1];
        List<Class <? extends AbstractTestSuite>> testSuites = Arrays.asList(
                AdvancedServerCore.class,
                AdvancedServerIM.class,
                AdvancedServerMobile.class,
                Conversations.class
        );
        for(Class<?extends AbstractTestSuite> testSuite : testSuites) {
            runTestSuite(testSuite, jid, password);
            System.out.println("\n");
        }
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
