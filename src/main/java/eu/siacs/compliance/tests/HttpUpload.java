package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;

public class HttpUpload extends AbstractServiceTest {

    public HttpUpload(XmppClient client) {
        super(client);
    }

    @Override
    public String getNamespace() {
        return "urn:xmpp:http:upload";
    }


    @Override
    public String getName() {
        return "XEP-0363: HTTP File Upload";
    }
}
