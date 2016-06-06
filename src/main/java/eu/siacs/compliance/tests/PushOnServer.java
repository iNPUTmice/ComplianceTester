package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;

/**
 * This test checks for availability of push on the server jid
 * The XEP specifics that the availability should be announced on the account
 * Prosody (the only implementation for now) does this the wrong way round
 * see Test 'Push' for a proper test
 */
public class PushOnServer extends AbstractDiscoTest {

    public PushOnServer(XmppClient client) {
        super(client);
    }

    @Override
    List<String> getNamespaces() {
        return Arrays.asList("urn:xmpp:push:0");
    }

    @Override
    boolean checkOnServer() {
        return true;
    }


    @Override
    public String getName() {
        return "XEP-0357: Push Notifications (on server)";
    }
}
