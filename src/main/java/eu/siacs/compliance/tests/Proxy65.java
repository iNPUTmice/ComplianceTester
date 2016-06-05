package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.bytestreams.s5b.model.Socks5ByteStream;

public class Proxy65 extends AbstractServiceTest {

    public Proxy65(XmppClient client) {
        super(client);
    }

    @Override
    public String getNamespace() {
        return Socks5ByteStream.NAMESPACE;
    }


    @Override
    public String getName() {
        return "XEP-0065: SOCKS5 Bytestreams (Proxy)";
    }
}
