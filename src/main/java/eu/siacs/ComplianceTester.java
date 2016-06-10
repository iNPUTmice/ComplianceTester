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
        Jid jid = Jid.of(args[0]);
        String password;
        if (args.length < 1 || args.length > 2) {
            System.err.println("java -jar ComplianceTester.jar username@domain.tld [password]");
            System.exit(1);
            return;
        } else if (args.length == 2) {
            password = args[1];
            AccountStore.storePassword(jid, password);
        } else {
            String storedPassword = AccountStore.getPassword(jid);
            if (storedPassword != null) {
                password = storedPassword;
            } else {
                System.err.println("password for "+jid+ " was not stored. trying to register");
                try {
                    password = RegistrationHelper.register(jid);
                    AccountStore.storePassword(jid, password);
                } catch (RegistrationHelper.RegistrationNotSupported e) {
                    System.err.println("server "+jid.getDomain()+" does not support registration");
                    System.exit(1);
                    return;
                } catch (RegistrationHelper.RegistrationFailed e) {
                    System.out.println("registration failed on server "+jid.getDomain()+" "+e.getMessage());
                    System.exit(1);
                    return;
                }

            }
        }
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
