package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;

public class MessageCarbons extends AbstractDiscoTest {

    public MessageCarbons(XmppClient client) {
        super(client);
    }

    @Override
    List<String> getNamespaces() {
        return Arrays.asList("urn:xmpp:carbons:2");
    }

    @Override
    boolean checkOnServer() {
        return true;
    }


    @Override
    public String getName() {
        return "XEP-0280: Message Carbons";
    }
}
