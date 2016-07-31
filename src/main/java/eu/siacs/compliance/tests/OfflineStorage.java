package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;

public class OfflineStorage extends AbstractDiscoTest {

    public OfflineStorage(XmppClient client) {
        super(client);
    }

    @Override
    public String getName() {
        return "XEP-0160: Best Practices for Handling Offline Messages";
    }

    @Override
    List<String> getNamespaces() {
        return Arrays.asList("msgoffline");
    }

    @Override
    boolean checkOnServer() {
        return true;
    }
}
