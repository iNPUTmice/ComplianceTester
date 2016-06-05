package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stream.model.StreamFeature;

public class StreamManagement extends AbstractStreamFeatureTest {

    public StreamManagement(XmppClient client) {
        super(client);
    }

    @Override
    Class<? extends StreamFeature> getStreamFeature() {
        return rocks.xmpp.extensions.sm.model.StreamManagement.class;
    }

    @Override
    public String getName() {
        return "XEP-0198: Stream Management";
    }
}
