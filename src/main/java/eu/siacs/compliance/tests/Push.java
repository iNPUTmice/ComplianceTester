package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;

public class Push extends AbstractDiscoTest {

    public Push(XmppClient client) {
        super(client);
    }

    @Override
    List<String> getNamespaces() {
        return Arrays.asList("urn:xmpp:push:0");
    }

    @Override
    boolean checkOnServer() {
        return false;
    }


    @Override
    public String getName() {
        return "XEP-0357: Push Notifications";
    }
}
