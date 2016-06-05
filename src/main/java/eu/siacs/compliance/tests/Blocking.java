package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;

public class Blocking extends AbstractDiscoTest {

    public Blocking(XmppClient client) {
        super(client);
    }

    @Override
    List<String> getNamespaces() {
        return Arrays.asList("urn:xmpp:blocking");
    }

    @Override
    boolean checkOnServer() {
        return true;
    }


    @Override
    public String getName() {
        return "XEP-0191: Blocking Command";
    }
}
