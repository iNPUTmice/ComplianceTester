package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.register.RegistrationManager;
import rocks.xmpp.extensions.register.model.Registration;

import java.util.concurrent.ExecutionException;

public class InBandRegistrationTest extends AbstractTest {

    public InBandRegistrationTest(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        final String domain = client.getConnectedResource().getDomain();
        final XmppClient testClient = XmppClient.create(domain);
        try {
            testClient.connect();
            RegistrationManager registrationManager = testClient.getManager(RegistrationManager.class);
            if(registrationManager.isRegistrationSupported().getResult()) {
                registrationManager.getRegistration().get();
                return Result.PASSED;
            }
            return Result.FAILED;
        } catch (Exception e) {
            return Result.FAILED;
        }
    }

    @Override
    public String getName() {
        return "XEP-0077: In-Band Registration";
    }
}