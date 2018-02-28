package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.register.RegistrationManager;

public class InBandRegistrationTest extends AbstractTest {

    public InBandRegistrationTest(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        String domain = client.getConnectedResource().getDomain();
        XmppClient testClient = XmppClient.create(domain);
        RegistrationManager registrationManager = testClient.getManager(RegistrationManager.class);
        try {
            if(registrationManager.isRegistrationSupported().getResult()) {
                return Result.PASSED;
            }
            return Result.FAILED;
        } catch (XmppException e) {
            e.printStackTrace();
            return Result.FAILED;
        }
    }

    @Override
    public String getName() {
        return "XEP-0077: In-Band Registration";
    }
}