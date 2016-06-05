package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;

public class CSI extends AbstractDiscoTest {

    public CSI(XmppClient client) {
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
        return "XEP-0352: Client State Indication";
    }
}
