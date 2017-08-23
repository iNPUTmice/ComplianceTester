package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MAM extends AbstractDiscoTest {

    public static final List<String> NAMESPACES = Arrays.asList("urn:xmpp:mam:0","urn:xmpp:mam:1","urn:xmpp:mam:2");

    public MAM(XmppClient client) {
        super(client);
    }

    @Override
    List<String> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    boolean checkOnServer() {
        return false;
    }


    @Override
    public String getName() {
        return "XEP-0313: Message Archive Management";
    }
}
