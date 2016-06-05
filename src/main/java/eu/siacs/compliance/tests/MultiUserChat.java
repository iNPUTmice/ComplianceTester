package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.muc.model.Muc;

public class MultiUserChat extends AbstractServiceTest {

    public MultiUserChat(XmppClient client) {
        super(client);
    }

    @Override
    public String getNamespace() {
        return Muc.NAMESPACE;
    }


    @Override
    public String getName() {
        return "XEP-0045: Multi-User Chat";
    }
}
