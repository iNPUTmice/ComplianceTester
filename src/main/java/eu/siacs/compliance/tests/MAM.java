package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;

public class MAM extends AbstractDiscoTest {

    public MAM(XmppClient client) {
        super(client);
    }

    @Override
    List<String> getNamespaces() {
        return Arrays.asList("urn:xmpp:mam:0","urn:xmpp:mam:1");
    }

    @Override
    boolean checkOnServer() {
        return true;
    }


    @Override
    public String getName() {
        return "XEP-0313: Message Archive Management";
    }
}
